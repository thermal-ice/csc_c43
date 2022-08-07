package MyBnB.services;

import MyBnB.models.basic.Review;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MostPopularNounPhrases {

  public static Map<Integer, Map<String,Integer>> getMostPopularNounPhrases(List<Review> reviewList){
    Map<Integer,Map<String,Integer>> listingToPhraseCountMap = new HashMap<>();

    for (Review currReview: reviewList){

      String[] reviewPhrases = currReview.getComments().split("\\?|\\.|\\!");
      List<String> reviewComments = Arrays.stream(reviewPhrases).map(sentence -> sentence.toLowerCase().trim()).collect(Collectors.toList());

      if (! listingToPhraseCountMap.containsKey(currReview.getListingID())){
        Map<String,Integer> phraseToCountMap = new HashMap<>();
        listingToPhraseCountMap.put(currReview.getListingID(),phraseToCountMap);
      }

      for (String sentence : reviewComments){
        listingToPhraseCountMap.get(currReview.getListingID()).merge(sentence,1,Integer::sum); // If key exists increment, else set as 1.
      }
    }
    return listingToPhraseCountMap;
  }

}
