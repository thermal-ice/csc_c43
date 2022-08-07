create table if not exists Amenities
(
    name varchar(50) charset utf8mb3 not null,
    type varchar(50) charset utf8mb3 not null,
    constraint Amenities_name_uindex
        unique (name)
);

alter table Amenities
    add primary key (name);

create table if not exists User
(
    sin varchar(11) not null,
    id int auto_increment
        primary key,
    name varchar(200) charset utf8mb3 not null,
    occupation varchar(100) charset utf8mb3 null,
    birthdate date not null,
    constraint User_sin_uindex
        unique (sin)
);

create table if not exists Host
(
    id int not null,
    constraint Host_id_uindex
        unique (id),
    constraint Host___fk_id
        foreign key (id) references User (id)
            on delete cascade
);

alter table Host
    add primary key (id);

create table if not exists Listing
(
    id int auto_increment,
    type varchar(30) charset utf8mb3 default 'house' not null,
    latitude decimal(8,6) null,
    longitude decimal(9,6) null,
    hostID int not null,
    avgPricePerNight float null,
    constraint Listing_id_uindex
        unique (id),
    constraint Listing_Host_id_fk
        foreign key (hostID) references Host (id)
            on delete cascade
);

alter table Listing
    add primary key (id);

create table if not exists Address
(
    listingID int not null,
    country varchar(50) charset utf8mb3 not null,
    postalCode varchar(10) charset utf8mb3 not null,
    province_territory varchar(30) charset utf8mb3 not null,
    city varchar(100) charset utf8mb3 null,
    addressLine varchar(500) charset utf8mb3 not null,
    constraint Address_listingID_uindex
        unique (listingID),
    constraint Address_Listing_id_fk
        foreign key (listingID) references Listing (id)
            on delete cascade
);

alter table Address
    add primary key (listingID);

create table if not exists Availabilities
(
    id int auto_increment,
    listingID int not null,
    pricePerNight float not null,
    startDate date not null,
    endDate date not null,
    constraint Availabilities_id_uindex
        unique (id),
    constraint Availabilities_Listing_id_fk
        foreign key (listingID) references Listing (id)
            on delete cascade
);

alter table Availabilities
    add primary key (id);

ALTER TABLE Availabilities
    ADD CONSTRAINT Check_availDates CHECK ( startDate <= endDate);

create table if not exists ListingAmenities
(
    listingID int not null comment 'part of primary key',
    amenity varchar(50) charset utf8mb3 not null comment 'part of primary key',
    constraint ListingAmenities_pk
        unique (listingID, amenity),
    constraint ListingAmenities_Amenities_name_fk
        foreign key (amenity) references Amenities (name),
    constraint ListingAmenities_Listing_id_fk
        foreign key (listingID) references Listing (id)
            on delete cascade
);

create table if not exists Renter
(
    id int not null,
    constraint Renter_id_uindex
        unique (id),
    constraint Renter___fk_id
        foreign key (id) references User (id)
            on delete cascade
);

alter table Renter
    add primary key (id);

create table Bookings
(
    pricePerNight float       not null,
    listingID     int         null,
    status        varchar(11) null,
    startDate     date        not null,
    endDate       date        not null,
    hostID        int         not null,
    renterID      int         not null,
    id            int auto_increment,
    constraint Bookings_id_uindex
        unique (id),
    constraint Bookings_Host_id_fk
        foreign key (hostID) references Host (id)
            on delete cascade,
    constraint Bookings_Listing_id_fk
        foreign key (listingID) references Listing (id),
    constraint Bookings_Renter_id_fk
        foreign key (renterID) references Renter (id)
            on delete cascade
);

alter table Bookings
    add primary key (id);

ALTER TABLE Bookings
    ADD CONSTRAINT Check_bookingDates CHECK ( startDate <= endDate);

create table if not exists PaymentInfo
(
    id int auto_increment,
    cardNumber varchar(20) not null,
    cardName varchar(200) charset utf8mb3 not null,
    expiryDate date null,
    renterID int not null,
    constraint PaymentInfo_id_uindex
        unique (id),
    constraint PaymentInfo_Renter_id_fk
        foreign key (renterID) references Renter (id)
            on delete cascade
);

alter table PaymentInfo
    add primary key (id);

create table if not exists Review
(
    id int auto_increment,
    comments text null,
    rating tinyint not null,
    bookingID int not null,
    listingID int not null,
    reviewerID int not null,
    revieweeID int not null,
    constraint Review_id_uindex
        unique (id),
    constraint Review_reviewerID_booking_uindex
        unique (reviewerID, bookingID),
    constraint Review_Bookings_id_fk
        foreign key (bookingID) references Bookings (id)
            on delete cascade,
    constraint Review_Listing_id_fk
        foreign key (listingID) references Listing (id)
            on delete cascade,
    constraint Review_User_id_fk
        foreign key (reviewerID) references User (id)
            on delete cascade,
    constraint Review_User_id_fk_2
        foreign key (revieweeID) references User (id)
            on delete cascade
);

alter table Review
    add primary key (id);

create table if not exists UserSearch
(
    id int auto_increment,
    amenity varchar(50) charset utf8mb3 not null comment 'part of primary key',
    searchCount int not null,
    constraint UserSearch_id_uindex
            unique (id),
    constraint UserSearch_Amenities_name_fk
            foreign key (amenity) references Amenities (name)
);

alter table UserSearch
    add primary key (id);

create trigger add_amenity after insert on Amenities for each row
insert into UserSearch (amenity, searchCount) values (NEW.name, 0);

create trigger add_availability after insert on Availabilities for each row
update Listing set avgPricePerNight = (select AVG(pricePerNight) from Availabilities where listingID=NEW.listingID) where id = NEW.listingID;

create trigger delete_availability after delete on Availabilities for each row
update Listing set avgPricePerNight = (select AVG(pricePerNight) from Availabilities where listingID=OLD.listingID) where id = OLD.listingID;

create trigger update_availability after update on Availabilities for each row
update Listing set avgPricePerNight = (select AVG(pricePerNight) from Availabilities where listingID=NEW.listingID) where id = NEW.listingID;



DROP PROCEDURE IF EXISTS sp_addAvailability;


DELIMITER //
CREATE PROCEDURE sp_addAvailability(IN in_listingID int, IN in_pricePerNight float,
                                    IN in_startDate DATE, IN in_endDate DATE)
sp: BEGIN
    START TRANSACTION;
    IF Exists (Select * FROM Availabilities where listingID = in_listingID AND startDate <= in_endDate AND endDate >= in_startDate) THEN
        Select ('AVAILABILITY_OVERLAPPING');
        LEAVE sp;
    end if;
    INSERT INTO Availabilities (listingID, pricePerNight, startDate, endDate) VALUE (in_listingID,in_pricePerNight,in_startDate,in_endDate);
    COMMIT;
    Select ('SUCCESS');
END //
DELIMITER ;



DROP PROCEDURE IF EXISTS sp_addBooking;
DELIMITER //
CREATE PROCEDURE sp_addBooking(IN in_renterID INT, IN in_listingID int,
                               IN in_startDate DATE, IN in_endDate DATE)
sp: BEGIN

    DECLARE existingAvailID INT;
    DECLARE existingPricePerNight FLOAT;
    DECLARE existingStartDate DATE;
    DECLARE existingEndDate DATE;
    DECLARE existingHostID INT;


    START TRANSACTION;
    IF NOT Exists (Select * FROM Availabilities where listingID = in_listingID AND startDate <= in_startDate AND in_endDate <= endDate) THEN
        Select (CONCAT('No Availabilities for ListingID:', in_listingID));
        LEAVE sp;
    end if;

    SELECT Availabilities.id,pricePerNight,startDate,endDate, hostID INTO existingAvailID,existingPricePerNight,existingStartDate,existingEndDate, existingHostID
    FROM Availabilities INNER JOIN Listing ON Availabilities.listingID = Listing.id where listingID = in_listingID AND startDate <= in_startDate AND in_endDate <= endDate;

    IF in_startDate = existingStartDate AND in_endDate = existingEndDate THEN
        DELETE FROM Availabilities WHERE id = existingAvailID;
    ELSEIF in_startDate = existingStartDate AND in_endDate < existingEndDate THEN
        UPDATE Availabilities SET startDate = DATE_ADD(in_endDate, INTERVAL 1 DAY) WHERE id = existingAvailID;
    ELSEIF existingStartDate < in_startDate AND in_endDate = existingEndDate THEN
        UPDATE Availabilities SET endDate = DATE_SUB(in_startDate, INTERVAL 1 DAY) WHERE id = existingAvailID;
    ELSE
        # Must create another availability and update the existing one, since it lies in between the two date boundaries
        # in_startDate < existingStartDate AND in_endDate < existingEndDate
        UPDATE Availabilities SET startDate = DATE_ADD(in_endDate, INTERVAL 1 DAY) WHERE id = existingAvailID;
        INSERT INTO Availabilities (listingID, pricePerNight, startDate, endDate) VALUE (in_listingID,existingPricePerNight,existingStartDate,DATE_SUB(in_startDate, INTERVAL 1 DAY));
    end if;
    INSERT INTO Bookings(pricePerNight, listingID, status, startDate, endDate, hostID, renterID)
        VALUE (existingPricePerNight,in_listingID,'BOOKED',in_startDate,in_endDate,existingHostID,in_renterID);

    COMMIT;
    Select ('SUCCESS');
END //
DELIMITER ;