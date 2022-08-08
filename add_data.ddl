/* adding id to better identify if user is renter or host
# hosts are in 100s, renters are in 200s */
insert into User (id, name, birthdate, occupation, sin)
values
    (1, 'Avery Marchmount', '2000-05-21', 'architect', '233 472 976'),
    (2, 'Afsaneh Ali', '1998-02-05', 'product manager', '951 558 030'),
    (3, 'William Fairgrie', '2000-05-21', 'student', '202 397 022'),
    (4, 'Afsaneh Ali', '1995-12-10', 'farmer', '233 834 928'),
    (5, 'Jaskaran Klein', '1995-12-10', 'civil servant', '936 828 987');

insert into Renter values (2);
insert into Renter values (3);
insert into Renter values (5);

insert into Host values (1);
insert into Host values (4);

/* First 5 listings are 10km around UTSC, rest are more than 10km. */
insert into Listing (id, type, latitude, longitude, hostID)
values
    (1, 'Apartment', '43.79678986790671', '-79.17036799080888',         1),
    (2, 'House',  '43.78288852948999', '-79.20516312657959',              1),
    (3, 'House', '43.77644879150473', '-79.25768022775479',             1),
    (4, 'Secondary Unit', '43.7650401461228', '-79.15159576483828',    1),
    (5, 'Unique space', '43.77730841204887', '-79.25755579307835',      4),
    (6, 'Bed and breakfast', '43.72849657610648', '-79.44928245355135', 4),
    (7, 'Bed and breakfast', '43.95152166364383', '-79.39049251233449', 4);

insert into Amenities (type, name)
values
    ('Bathroom', 'Bathtub'),
    ('Bathroom', 'Bidet'),
    ('Bathroom', 'Body soap'),
    ('Bathroom', 'Cleaning products'),
    ('Bathroom', 'Conditioner'),
    ('Bathroom', 'Hair dryer'),
    ('Bathroom', 'Hot water'),
    ('Bathroom', 'Outdoor shower'),
    ('Bathroom', 'Shampoo'),
    ('Bathroom', 'Shower gel'),

    ('Bedroom and laundry', 'Essentials'),
    ('Bedroom and laundry', 'Bed linens'),
    ('Bedroom and laundry', 'Clothing storage'),
    ('Bedroom and laundry', 'Dryer'),
    ('Bedroom and laundry', 'Drying rack for clothing'),
    ('Bedroom and laundry', 'Extra pillows and blankets'),
    ('Bedroom and laundry', 'Hangers'),
    ('Bedroom and laundry', 'Iron'),
    ('Bedroom and laundry', 'Mosquito net'),
    ('Bedroom and laundry', 'Room-darkening shades'),
    ('Bedroom and laundry', 'Safe'),
    ('Bedroom and laundry', 'Washer'),

    ('Entertainment', 'Ethernet connection'),
    ('Entertainment', 'Game console'),
    ('Entertainment', 'Piano'),
    ('Entertainment', 'Ping pong table'),
    ('Entertainment', 'Pool table'),
    ('Entertainment', 'Record player'),
    ('Entertainment', 'Sound system'),
    ('Entertainment', 'TV'),

    ('Family', 'Baby bath'),
    ('Family', 'Baby monitor'),
    ('Family', 'BabyCooking basicsChanging table'),
    ('Family', 'Children’s books and toys'),
    ('Family', 'Children’s dinnerware'),
    ('Family', 'Crib'),
    ('Family', 'Fireplace guards'),
    ('Family', 'High chair'),
    ('Family', 'Outlet covers'),
    ('Family', 'Pack ’n play/Travel crib'),
    ('Family', 'Table corner guards'),
    ('Family', 'Window guards'),
    ('Family', 'Board games'),

    ('Heating and cooling', 'Air conditioning'),
    ('Heating and cooling', 'Ceiling fan'),
    ('Heating and cooling', 'Heating'),
    ('Heating and cooling', 'Indoor fireplace'),
    ('Heating and cooling', 'Portable fans'),

    ('Home safety', 'Carbon monoxide alarm'),
    ('Home safety', 'Fire extinguisher'),
    ('Home safety', 'First aid kit'),
    ('Home safety', 'Smoke alarm'),

    ('Internet and office', 'Dedicated workspace'),
    ('Internet and office', 'Pocket wifi'),
    ('Internet and office', 'Wifi'),

    ('Kitchen and dining', 'Baking sheet'),
    ('Kitchen and dining', 'Barbecue utensils'),
    ('Kitchen and dining', 'Bread maker'),
    ('Kitchen and dining', 'Coffee maker'),
    ('Kitchen and dining', 'Cooking basics'),
    ('Kitchen and dining', 'Dining table'),
    ('Kitchen and dining', 'Dishes and silverware'),
    ('Kitchen and dining', 'Dishwasher'),
    ('Kitchen and dining', 'Freezer'),
    ('Kitchen and dining', 'Hot water kettle'),
    ('Kitchen and dining', 'Kitchen'),
    ('Kitchen and dining', 'Microwave'),
    ('Kitchen and dining', 'Mini fridge'),
    ('Kitchen and dining', 'Oven'),
    ('Kitchen and dining', 'Refrigerator'),
    ('Kitchen and dining', 'Stove'),
    ('Kitchen and dining', 'Toaster'),
    ('Kitchen and dining', 'Rice cooker'),
    ('Kitchen and dining', 'Trash compactor'),
    ('Kitchen and dining', 'Wine glasses'),

    ('Location features', 'Beach access'),
    ('Location features', 'Lake access'),
    ('Location features', 'Laundromat nearby'),
    ('Location features', 'Private entrance'),
    ('Location features', 'Ski-in/Ski-out'),
    ('Location features', 'Waterfront'),

    ('Outdoor', 'Backyard'),
    ('Outdoor', 'BBQ grill'),
    ('Outdoor', 'Beach essentials'),
    ('Outdoor', 'Bikes'),
    ('Outdoor', 'Boat slip'),
    ('Outdoor', 'Fire pit'),
    ('Outdoor', 'Kayak'),
    ('Outdoor', 'Outdoor dining area'),
    ('Outdoor', 'Outdoor furniture'),
    ('Outdoor', 'Patio or balcony'),

    ('Parking and facilities', 'Elevator'),
    ('Parking and facilities', 'EV charger'),
    ('Parking and facilities', 'Free parking on premises'),
    ('Parking and facilities', 'Free street parking'),
    ('Parking and facilities', 'Gym'),
    ('Parking and facilities', 'Hot tub'),
    ('Parking and facilities', 'Paid parking off premises'),
    ('Parking and facilities', 'Paid parking on premises'),
    ('Parking and facilities', 'Pool'),
    ('Parking and facilities', 'Sauna'),
    ('Parking and facilities', 'Single level home'),

    ('Services', 'Breakfast'),
    ('Services', 'Cleaning before checkout'),
    ('Services', 'Long-term stays allowed'),
    ('Services', 'Luggage drop-off allowed'),
    ('Services', 'Self check-in'),
    ('Services', 'Pets allowed'),
    ('Services', 'Lockbox');

insert into ListingAmenities (listingID, amenity)
values
  (1, 'Microwave'),
  (1, 'Washer'),
  (1, 'Dryer'),
  (1, 'Essentials'),
  (1, 'Clothing storage'),
  (1, 'Board games'),
  (1, 'Wifi'),
  (1, 'Fire pit'),

  (2, 'Dining table'),
  (2, 'Hair dryer'),
  (2, 'Cleaning products'),

  (3, 'Dedicated workspace'),
  (3, 'Refrigerator'),
  (3, 'Cooking basics'),
  (3, 'Dishes and silverware'),
  (3, 'Coffee maker'),

  (4, 'Dining table'),
  (4, 'Hair dryer'),
  (4, 'Cleaning products'),
  (4, 'Coffee maker'),

  (5, 'Dining table'),
  (5, 'Hair dryer'),
  (5, 'Cleaning products'),
  (5, 'Coffee maker'),

  (6, 'Dining table'),
  (6, 'Hair dryer'),
  (6, 'Cleaning products'),
  (6, 'Coffee maker'),
  (6, 'Fire pit'),

  (7, 'Kayak'),
  (7, 'BBQ grill'),
  (7, 'Fire pit'),
  (7, 'Game console'),
  (7, 'Piano'),
  (7, 'Table corner guards'),
  (7, 'Hot water');

insert into Address (listingID, addressLine, city, province_territory, postalCode, country)
values
    (1, '2089 Columbia Road', 'Buffalo', 'NY', 'H7M 4V2', 'USA'), # booking
    (2, '3230 Travis Street', 'Buffalo', 'NY', 'P5E 1E8', 'USA'),
    (3, '872 Bubby Drive', 'Aurora', 'ON', 'H1W 0T2', 'Canada'), # booking
    (4, '412 Sardis Station', 'Richmondhill', 'ON', 'N8P 2M9', 'Canada'),
    (5, '3945 Emily Drive', 'Aurora', 'ON', 'H1W 0T2', 'Canada'), # booking
    (6, '1076 Travis Street', 'Scarborough', 'ON', 'B1C 6T0', 'Canada'), #booking
    (7, '4604 Glen Street', 'Buffalo', 'NY', 'N0J 6K6', 'USA'); # booking

/* 2020, 2021, 2022 availabilities. */
INSERT INTO Availabilities (listingID, pricePerNight, startDate, endDate) VALUES
(1, 60.99, '2020-09-03', '2020-09-05'),
(1, 100.99, '2020-11-03', '2020-11-05'),
(1, 80, '2022-01-03', '2022-01-05'),
(2, 200, '2021-11-03', '2021-11-05'),
(3, 50, '2021-12-03', '2021-12-25'),
(3, 149, '2022-08-06', '2022-11-30'),
(4, 44, '2022-11-03', '2022-11-05'),
(5, 90, '2022-11-05', '2022-11-10'),
(6, 140.1, '2022-11-07', '2022-11-09'),
(7, 45.5, '2022-11-10', '2022-11-29');

/* Listing 3, 5, 7 have bookings. */
insert into Bookings (id, renterID, hostID, listingID, endDate, startDate, status, pricePerNight)
values
  (10, 5, 1, 1, '2020-10-05', '2020-10-04', 'BOOKED', 60.99),

  (20, 2, 1, 3, '2021-11-06', '2021-11-03', 'BOOKED', 50),
  (25, 2, 1, 3, '2021-11-18', '2021-11-17', 'CANCELLED', 50),
  (30, 2, 1, 3, '2021-11-07', '2021-11-07', 'BOOKED', 50),
  (40, 3, 1, 3, '2021-11-15', '2021-11-09', 'BOOKED', 50),

  (50, 2, 4, 5, '2022-11-18', '2022-11-15', 'BOOKED', 90),

  (60, 2, 4, 6, '2022-11-03', '2022-11-03', 'BOOKED', 140.10),

  (70, 2, 4, 7, '2022-11-23', '2022-11-15', 'CANCELLED', 45.5),
  (80, 2, 4, 7, '2022-11-23', '2022-11-15', 'CANCELLED', 45.5),

  (90, 5, 1, 1, '2022-01-04', '2022-01-03', 'CANCELLED', 80.0);

insert into PaymentInfo (id, cardNumber, cardName, expiryDate, renterID)
values
  (1, '4485240828037307', 'Afsaneh Ali', '2028-02-05', 2),
  (2, '5175635227696920', 'William Fairgrie', '2025-02-05', 3);


insert into Review (id, rating, bookingID, listingID, reviewerID, revieweeID, comments)
values
  /* Renter 2 and 3 comments on listing 3 with host 1. */
  (1, 4.0, 20, 3, 2, 1, 'Great location. Lovely space. Quick communication.'),
  (2, 4.5, 30, 3, 3, 1, 'Great location and beautiful home! Anne was very responsive and provided any support we needed.'),
  /* Renter 2 comments on listing 5 with host 4. Booking 20. */
  (3, 5.0, 50, 5, 2, 4, 'Our stay at Annes place was great - it is a fantastic location with amenities on the water, and communication with her was flawless.'),
  (4, 3.0, 50, 5, 4, 2, 'fantastic location with amenities! flawless.'),
  /* Host 1 comments on renter 2 rented listing 3. */
  (5, 4.0, 20, 3, 1, 2, 'Afsaneh and his guest were very quiet and clean. Great location.');


call sp_addAvailability(1,150,'2022-07-05','2022-07-20');

call sp_addBooking(3,1,'2022-07-09','2022-07-19');

call sp_cancelBooking(91);

call sp_addReview(10,5,5,'It was a fantastic place to stay at!');