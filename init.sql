CREATE USER insurance_service_user WITH password 'insurance_service';
CREATE USER customer_service_user WITH password 'customer_service';

CREATE DATABASE customer_service OWNER customer_service_user;
CREATE DATABASE insurance_service OWNER insurance_service_user;