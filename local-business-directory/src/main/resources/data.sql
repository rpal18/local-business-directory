-- ==================================================================================
-- 📍 DATA INITIALIZATION SCRIPT - LOCAL BUSINESS DIRECTORY
-- Context: Kanpur, Uttar Pradesh, India
-- SRID: 4326 (WGS 84) | Order: ST_MakePoint(LONGITUDE, LATITUDE)
-- ==================================================================================

-- ----------------------------------------------------------------------------------
-- 1. INSERT DUMMY USER (Center Point: Civil Lines, Kanpur)
-- ----------------------------------------------------------------------------------
INSERT INTO users (name, email, password, contact_number, dob, address, location)
SELECT 'Rohit Tester', 'rohit@test.com', 'password123', '9876543210', '2000-01-01', 'Civil Lines, Kanpur',
       ST_SetSRID(ST_MakePoint(80.3319, 26.4499), 4326)::geography
WHERE NOT EXISTS (SELECT 1 FROM users WHERE email = 'rohit@test.com');


-- ----------------------------------------------------------------------------------
-- 2. INSERT BUSINESSES (20 Records)
-- ----------------------------------------------------------------------------------

-- 1. RESTAURANT - Mocha Cafe (Swaroop Nagar) ~2.5km
INSERT INTO businesses (business_name, address, contact_number, email, website, category, location, created_at, updated_at)
SELECT 'Mocha Cafe', 'Swaroop Nagar, Kanpur', '9988776655', 'hello@mocha.com', 'www.mocha.co.in', 'RESTAURANT',
       ST_SetSRID(ST_MakePoint(80.3200, 26.4650), 4326)::geography, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM businesses WHERE email = 'hello@mocha.com');

-- 2. HOSPITAL - Regency Hospital (Sarvodaya Nagar) ~4km
INSERT INTO businesses (business_name, address, contact_number, email, website, category, location, created_at, updated_at)
SELECT 'Regency Hospital', 'Sarvodaya Nagar, Kanpur', '0512-308111', 'info@regency.com', 'www.regencyhospital.in', 'HOSPITAL',
       ST_SetSRID(ST_MakePoint(80.3050, 26.4800), 4326)::geography, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM businesses WHERE email = 'info@regency.com');

-- 3. SHOPPING - Z Square Mall (The Mall) ~0.5km (Very Close)
INSERT INTO businesses (business_name, address, contact_number, email, website, category, location, created_at, updated_at)
SELECT 'Z Square Electronic Hub', 'The Mall, Kanpur', '9876543211', 'sales@zsquarehub.com', 'www.zsquare.com', 'ELECTRONIC_STORE',
       ST_SetSRID(ST_MakePoint(80.3350, 26.4510), 4326)::geography, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM businesses WHERE email = 'sales@zsquarehub.com');

-- 4. STATIONERY - Universal Books (The Mall) ~0.6km
INSERT INTO businesses (business_name, address, contact_number, email, website, category, location, created_at, updated_at)
SELECT 'Universal Booksellers', 'The Mall Road, Kanpur', '9839012345', 'books@universal.com', NULL, 'STATIONERY',
       ST_SetSRID(ST_MakePoint(80.3360, 26.4520), 4326)::geography, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM businesses WHERE email = 'books@universal.com');

-- 5. SPORTS - Decathlon (Logix City Center - Hypothetical Loc) ~5km
INSERT INTO businesses (business_name, address, contact_number, email, website, category, location, created_at, updated_at)
SELECT 'Decathlon Sports', 'GT Road, Kanpur', '8877665544', 'manager@decathlon.in', 'www.decathlon.in', 'SPORTS_SHOP',
       ST_SetSRID(ST_MakePoint(80.3100, 26.4900), 4326)::geography, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM businesses WHERE email = 'manager@decathlon.in');

-- 6. BIKE - Royal Enfield (Civil Lines) ~0.3km
INSERT INTO businesses (business_name, address, contact_number, email, website, category, location, created_at, updated_at)
SELECT 'Royal Enfield Showroom', 'Civil Lines, Kanpur', '9123456789', 'sales@rekanpur.com', 'www.royalenfield.com', 'BIKE_SHOWROOM',
       ST_SetSRID(ST_MakePoint(80.3330, 26.4505), 4326)::geography, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM businesses WHERE email = 'sales@rekanpur.com');

-- 7. FOOD STALL - Thaggu Ke Laddu (Bada Chauraha) ~1.5km
INSERT INTO businesses (business_name, address, contact_number, email, website, category, location, created_at, updated_at)
SELECT 'Thaggu Ke Laddu', 'Bada Chauraha, Kanpur', '9415012345', 'sweet@thaggu.com', NULL, 'FOOD_STALL',
       ST_SetSRID(ST_MakePoint(80.3450, 26.4600), 4326)::geography, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM businesses WHERE email = 'sweet@thaggu.com');

-- 8. MEDICAL - Apollo Pharmacy (Naveen Market) ~1.2km
INSERT INTO businesses (business_name, address, contact_number, email, website, category, location, created_at, updated_at)
SELECT 'Apollo Pharmacy', 'Naveen Market, Kanpur', '7890123456', 'naveen@apollo.com', 'www.apollopharmacy.in', 'MEDICAL_STORE',
       ST_SetSRID(ST_MakePoint(80.3380, 26.4620), 4326)::geography, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM businesses WHERE email = 'naveen@apollo.com');

-- 9. SPORTS COMPLEX - Green Park Stadium (Civil Lines) ~1.0km
INSERT INTO businesses (business_name, address, contact_number, email, website, category, location, created_at, updated_at)
SELECT 'Green Park Stadium', 'Civil Lines, Kanpur', '0512-234567', 'admin@greenpark.com', NULL, 'SPORTS_COMPLEX',
       ST_SetSRID(ST_MakePoint(80.3458, 26.4716), 4326)::geography, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM businesses WHERE email = 'admin@greenpark.com');

-- 10. CAR SHOWROOM - Toyota (GT Road) ~6km
INSERT INTO businesses (business_name, address, contact_number, email, website, category, location, created_at, updated_at)
SELECT 'Sunny Toyota', 'GT Road, Kanpur', '9911223344', 'sales@sunnytoyota.com', 'www.toyota.com', 'CAR_SHOWROOM',
       ST_SetSRID(ST_MakePoint(80.2900, 26.5000), 4326)::geography, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM businesses WHERE email = 'sales@sunnytoyota.com');

-- 11. PETROL PUMP - Indian Oil (Cantt) ~3km
INSERT INTO businesses (business_name, address, contact_number, email, website, category, location, created_at, updated_at)
SELECT 'Indian Oil Pump', 'Cantt, Kanpur', '9876598765', 'iocl@cantt.com', NULL, 'PETROL_PUMP',
       ST_SetSRID(ST_MakePoint(80.3600, 26.4550), 4326)::geography, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM businesses WHERE email = 'iocl@cantt.com');

-- 12. SHOE STORE - Red Tape (Mall Road) ~0.7km
INSERT INTO businesses (business_name, address, contact_number, email, website, category, location, created_at, updated_at)
SELECT 'Red Tape Outlet', 'Mall Road, Kanpur', '8899001122', 'store@redtape.com', 'www.redtape.com', 'SHOE_STORE',
       ST_SetSRID(ST_MakePoint(80.3370, 26.4530), 4326)::geography, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM businesses WHERE email = 'store@redtape.com');

-- 13. RESTAURANT - Haveli (Civil Lines) ~0.2km
INSERT INTO businesses (business_name, address, contact_number, email, website, category, location, created_at, updated_at)
SELECT 'Haveli Restaurant', 'Civil Lines, Kanpur', '9000011111', 'dine@haveli.com', NULL, 'RESTAURANT',
       ST_SetSRID(ST_MakePoint(80.3315, 26.4495), 4326)::geography, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM businesses WHERE email = 'dine@haveli.com');

-- 14. MEDICAL - Gupta Medicos (Kakadeo) ~4.5km
INSERT INTO businesses (business_name, address, contact_number, email, website, category, location, created_at, updated_at)
SELECT 'Gupta Medicos', 'Kakadeo, Kanpur', '9838000000', 'gupta@medicos.com', NULL, 'MEDICAL_STORE',
       ST_SetSRID(ST_MakePoint(80.2950, 26.4750), 4326)::geography, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM businesses WHERE email = 'gupta@medicos.com');

-- 15. ELECTRONIC - Reliance Digital (South X Mall) ~8km
INSERT INTO businesses (business_name, address, contact_number, email, website, category, location, created_at, updated_at)
SELECT 'Reliance Digital', 'Kidwai Nagar, Kanpur', '1800-100-200', 'support@reliancedigital.in', 'www.reliancedigital.in', 'ELECTRONIC_STORE',
       ST_SetSRID(ST_MakePoint(80.3200, 26.4200), 4326)::geography, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM businesses WHERE email = 'support@reliancedigital.in');

-- 16. HOSPITAL - Hallet (GSVM) ~3.5km
INSERT INTO businesses (business_name, address, contact_number, email, website, category, location, created_at, updated_at)
SELECT 'Hallet Hospital', 'Swaroop Nagar, Kanpur', '0512-250000', 'help@hallet.com', NULL, 'HOSPITAL',
       ST_SetSRID(ST_MakePoint(80.3150, 26.4700), 4326)::geography, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM businesses WHERE email = 'help@hallet.com');

-- 17. CAR SHOWROOM - Hyundai (Govind Nagar) ~6.5km
INSERT INTO businesses (business_name, address, contact_number, email, website, category, location, created_at, updated_at)
SELECT 'Trident Hyundai', 'Govind Nagar, Kanpur', '9797979797', 'sales@trident.com', NULL, 'CAR_SHOWROOM',
       ST_SetSRID(ST_MakePoint(80.3000, 26.4300), 4326)::geography, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM businesses WHERE email = 'sales@trident.com');

-- 18. STATIONERY - Pustak Bhawan (Parade) ~1.8km
INSERT INTO businesses (business_name, address, contact_number, email, website, category, location, created_at, updated_at)
SELECT 'Pustak Bhawan', 'Parade, Kanpur', '9111222333', 'info@pustakbhawan.com', NULL, 'STATIONERY',
       ST_SetSRID(ST_MakePoint(80.3420, 26.4650), 4326)::geography, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM businesses WHERE email = 'info@pustakbhawan.com');

-- 19. FOOD STALL - Badnam Kulfi (Civil Lines) ~0.4km
INSERT INTO businesses (business_name, address, contact_number, email, website, category, location, created_at, updated_at)
SELECT 'Badnam Kulfi', 'Civil Lines, Kanpur', '8888899999', 'yummy@kulfi.com', NULL, 'FOOD_STALL',
       ST_SetSRID(ST_MakePoint(80.3340, 26.4480), 4326)::geography, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM businesses WHERE email = 'yummy@kulfi.com');

-- 20. RESTAURANT - Tunday Kababi (Lucknow) ~80km (Testing Far Distance)
-- This record should NOT appear if radius is 5000m (5km)
INSERT INTO businesses (business_name, address, contact_number, email, website, category, location, created_at, updated_at)
SELECT 'Tunday Kababi', 'Aminabad, Lucknow', '0522-222222', 'info@tunday.com', NULL, 'RESTAURANT',
       ST_SetSRID(ST_MakePoint(80.9200, 26.8500), 4326)::geography, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM businesses WHERE email = 'info@tunday.com');