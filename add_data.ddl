insert into User (id, name, birthdate, occupation, sin)
values
    (1, 'Avery Marchmount', '2000-05-21', 'architect', '233 472 976'),
    (2, 'Afsaneh Ali', '1998-02-05', 'product manager', '951 558 030'),
    (3, 'William Fairgrie', '2000-05-21', 'student', '202 397 022'),
    (4, 'Afsaneh Ali', '1995-12-10', 'farmer', '233 834 928');

insert into Renter values (2);
insert into Renter values (3);

insert into Host values (1);
insert into Host values (4);

/* Two houses. Fix addressID. */
insert into Listing (id, type, latitude, longitude, hostID)
values
    (1, 'Apartment', '22.784762', '-32.100073', 1),
    (2, 'House', '88.795449', '-53.210433',  1),
    (3, 'House', '65.795449', '-23.210433', 1),
    (4, 'Secondary Unit', '34.794959', '-79.210433', 1),
    (5, 'Unique space', '43.795959', '-99.210433', 4),
    (6, 'Bed and breakfast', '43.795959', '-82.220433', 4),
    (7, 'Bed and breakfast', '55.795959', '-80.220433', 4);

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

  (4, 'Coffee maker'),
  (4, 'Baking sheet'),
  (4, 'Outdoor shower'),
  (4, 'Air conditioning'),
  (4, 'Body soap'),
  (4, 'Shampoo'),

  (6, 'Fire pit'),
  (6, 'Lake access'),

  (7, 'Kayak'),
  (7, 'BBQ grill'),
  (7, 'Fire pit'),
  (7, 'Game console'),
  (7, 'Piano'),
  (7, 'Table corner guards'),
  (7, 'Hot water');

insert into Address (listingID, addressLine, city, province_territory, postalCode, country)
values
    (1, '2089 Columbia Road', 'Scarborough', 'ON', 'H7M 4V2', 'Canada'),
    (2, '3230 Travis Street', 'Newmarket', 'ON', 'P5E 1E8', 'Canada'),
    (3, '872 Bubby Drive', 'Aurora', 'ON', 'H1W 0T2', 'Canada'),
    (4, '412 Sardis Station', 'Richmondhill', 'ON', 'N8P 2M9', 'Canada'),
    (5, '3945 Emily Drive', 'Vaughan', 'ON', 'N0J 6K4', 'Canada'),
    (6, '1076 Travis Street', 'Scarborough', 'ON', 'B1C 6T0', 'Canada'),
    (7, '4604 Glen Street', 'Newmarket', 'ON', 'N0J 6K5', 'Canada');

/* Give valid date ranges. Include different prices per time slot. */
insert into Availabilities (pricePerNight, endDate, startDate, listingID)
values
    (60.99, '2022-10-05', '2022-10-03', 1),
    (100.99, '2022-11-05', '2022-11-03', 1),
    (200.00, '2022-11-05', '2022-11-03', 2),
    (50.00, '2022-11-05', '2022-11-03', 3),
    (44.00, '2022-11-05', '2022-11-03', 4),
    (90.00, '2022-11-05', '2022-11-03', 5),
    (140.10, '2022-11-05', '2022-11-03', 6),
    (45.50, '2022-11-05', '2022-11-03', 7);

insert into Bookings (id, renterID, hostID, availabilityID, endDate, startDate, status)
values
  (10, 2, 1, 3, '2022-02-05', '2022-02-03', 'RESOLVED'),
  (15, 3, 1, 3, '2022-05-08', '2022-05-01', 'RESOLVED'),
  (20, 2, 4, 5, '2022-02-10', '2022-02-09', 'RESOLVED'),
  (25, 2, 4, 5, '2022-07-15', '2022-07-10', 'CANCELLED');

insert into PaymentInfo (id, cardNumber, cardName, expiryDate, renterID)
values
  (1, '4485240828037307', 'Afsaneh Ali', '2028-02-05', 2),
  (2, '5175635227696920', 'William Fairgrie', '2025-02-05', 3);


insert into Review (id, rating, bookingID, listingID, reviewerID, revieweeID, comments)
values
  /* Renter 2 and 3 comments on listing 3 with host 1. */
  (1, 4.0, 10, 3, 2, 1, 'Great location. Lovely space. Quick communication.'),
  (2, 4.5, 15, 3, 3, 1, 'Great location and beautiful home! Anne was very responsive and provided any support we needed.'),
  /* Renter 2 comments on listing 5 with host 4. Booking 20. */
  (3, 5.0, 20, 5, 2, 4, 'Our stay at Annes place was great - it is a fantastic location with amenities on the water, and communication with her was flawless.'),
  /* Host 1 comments on renter 2 rented listing 3. */
  (4, 4.0, 10, 3, 1, 2, 'Afsaneh and his guest were very quiet and clean.');


