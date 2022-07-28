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
	id int auto_increment
		primary key,
	name varchar(200) charset utf8mb3 not null,
	birthdate date not null,
	occupation varchar(100) charset utf8mb3 null,
	sin varchar(11) not null,
	isActive tinyint not null,
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
);

alter table Host
	add primary key (id);

create table if not exists Listing
(
	id int auto_increment,
	type varchar(30) charset utf8mb3 default 'house' not null,
	latitude decimal(8,6) null,
	longitude decimal(9,6) null,
	addressID int not null,
	hostID int not null,
	constraint Listing_id_uindex
		unique (id),
	constraint Listing_Host_id_fk
		foreign key (hostID) references Host (id)
);

alter table Listing
	add primary key (id);

create table if not exists Address
(
	listingID int auto_increment
		primary key,
	addressLine varchar(500) charset utf8mb3 not null,
	city varchar(100) charset utf8mb3 null,
	`province_territory` varchar(30) charset utf8mb3 not null,
	postalCode varchar(10) charset utf8mb3 not null,
	country varchar(50) charset utf8mb3 not null,
	constraint Address_Listing_id_fk
		foreign key (listingID) references Listing (id)
);

create table if not exists Availabilities
(
	pricePerNight float not null,
	endDate date not null,
	startDate date not null,
	listingID int not null,
	primary key (listingID, startDate),
	constraint Availabilities_Listing_id_fk
		foreign key (listingID) references Listing (id)
);

create index Availabilities_listingID_index
	on Availabilities (listingID);

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
);

create table if not exists Renter
(
	id int not null,
	constraint Renter_id_uindex
		unique (id),
	constraint Renter___fk_id
		foreign key (id) references User (id)
);

alter table Renter
	add primary key (id);

create table if not exists Bookings
(
	id int auto_increment,
	renterID int not null,
	hostID int not null,
	listingID int not null,
	endDate date not null,
	startDate date not null,
	status varchar(11) null,
	constraint Bookings_id_uindex
		unique (id),
	constraint Bookings_Host_id_fk
		foreign key (hostID) references Host (id),
	constraint Bookings_Listing_id_fk
		foreign key (listingID) references Listing (id),
	constraint Bookings_Renter_id_fk
		foreign key (renterID) references Renter (id)
);

alter table Bookings
	add primary key (id);

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
);

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
	constraint Review_Bookings_id_fk
		foreign key (bookingID) references Bookings (id),
	constraint Review_Listing_id_fk
		foreign key (listingID) references Listing (id),
	constraint Review_User_id_fk
		foreign key (reviewerID) references User (id),
	constraint Review_User_id_fk_2
		foreign key (revieweeID) references User (id)
);

alter table Review
	add primary key (id);

ALTER TABLE PaymentInfo ADD CONSTRAINT cardNumberIsNumeric CHECK ( REGEXP_LIKE(cardNumber, '^[0-9]+$'));