package MyBnB.services;

import MyBnB.controller.ListingController;
import MyBnB.repository.implementations.ListingRepository;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ListingSearchQueryBuilder {

  @Autowired
  private ListingRepository listingRepository;


  private String addQueryFilter(String baseQuery, String filter){
    return baseQuery + " id IN (" + filter + ") AND ";
  }

  private boolean isValidPostalCode(String postalCode){

    if(postalCode == null){
      return false;
    }
    String postalCodeWithoutSpaces = postalCode.replaceAll(" ", "");
    if (!postalCodeWithoutSpaces.matches("^[a-zA-Z0-9]*$") ||
        postalCodeWithoutSpaces.length() != 6){
      return false;
    }
    return true;

  }

  public String buildSQLQueryStringFromParams(Double latitude,
                                                     Double longitude,
                                                     Double radius,
                                                     String postalCode,
                                                     String addressLine,
                                                     Double minPrice,
                                                     Double maxPrice,
                                                     List<String> amenitiesList,
                                                     LocalDate startDate,
                                                     LocalDate endDate,
                                                     ListingController.OrderBy orderBy){

    String queryPrefix = "SELECT * ";
    String query = "FROM Listing WHERE";
    boolean canOrderByDistance = false;

    if (latitude != null && longitude != null && radius != null){
      query = addQueryFilter(query,String.format("SELECT distinct id as distance FROM Listing WHERE SQRT(POW(%f - latitude, 2) + POW(%f - longitude,2)) < %f",latitude,longitude,radius));
      queryPrefix = "SELECT *, SQRT(POW("+latitude+" - latitude, 2) + POW("+longitude+" - longitude,2)) as distance ";
      canOrderByDistance = true;
    }
    if(postalCode != null && isValidPostalCode(postalCode)){
      String postalCodeFSA = postalCode.substring(0,3);
      query = addQueryFilter(query,"SELECT DISTINCT L.id FROM Address INNER JOIN Listing L ON Address.listingID = L.id WHERE postalCode LIKE CONCAT( '"+ postalCodeFSA + "','%')");
    }
    if(addressLine != null){
      query = addQueryFilter(query, "Select L.id from Address inner join Listing L on Address.listingID = L.id Where addressLine like CONCAT('%', '"+ addressLine+"' ,'%')");
    }
    if (minPrice != null){
      query = addQueryFilter(query, "Select distinct L.id FROM Listing L INNER JOIN Availabilities A on L.id = A.listingID WHERE pricePerNight > " + minPrice);
    }
    if (maxPrice != null){
      query = addQueryFilter(query,"Select distinct L.id FROM Listing L INNER JOIN Availabilities A on L.id = A.listingID WHERE pricePerNight < "+ maxPrice);
    }
    if(amenitiesList != null && !amenitiesList.isEmpty()){
      String sqlQuery = listingRepository.getQueryToSearchByAmenities(amenitiesList);
      query = addQueryFilter(query,sqlQuery);
    }
    if(startDate != null){
      query = addQueryFilter(query,"Select distinct L.id From Listing L inner join Availabilities A on L.id = A.listingID and '"+startDate.toString()+"' <= DATE(endDate)");
    }
    if (endDate != null){
      query = addQueryFilter(query,"Select distinct L.id From Listing L inner join Availabilities A on L.id = A.listingID and DATE(startDate) <= '"+endDate.toString() +"'" );
    }

    String querySuffix = (orderBy == null)? " TRUE;" : switch (orderBy){
      case NONE -> " TRUE;";
      case DISTANCE -> canOrderByDistance? " TRUE ORDER BY distance ASC;" : " TRUE;";
      case PRICE_ASC -> " TRUE ORDER BY avgPricePerNight ASC;";
      case PRICE_DESC -> " TRUE ORDER BY avgPricePerNight DESC;";
    };

    String retQuery = queryPrefix + query + querySuffix;
    System.out.println(retQuery);
    return retQuery;
  }


}
