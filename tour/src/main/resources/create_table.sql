use tour;
CREATE TABLE tblTour (
    code_tour VARCHAR(100) PRIMARY KEY,
    tourname VARCHAR(100),
    departure_location VARCHAR(10),
    description VARCHAR(250),
    destination VARCHAR(100)
);

CREATE TABLE tblTourDeparture (
    id INT(10) PRIMARY KEY,
    departure_date DATE,
    end_date DATE,
    code_tour VARCHAR(100),
    FOREIGN KEY (code_tour) REFERENCES tblTour(code_tour)
);

CREATE TABLE tblPriceTicket (
    id INT(10) PRIMARY KEY,
    min_group_size INT(10),
    max_group_size INT(10),
    price_per_person FLOAT(10),
    id_departure INT(10),
    FOREIGN KEY (id_departure) REFERENCES tblTourDeparture(id)
);

CREATE TABLE tblUser (
    code_user VARCHAR(100) PRIMARY KEY,
    username VARCHAR(20),
    full_name VARCHAR(50),
    email VARCHAR(50),
    password VARCHAR(100),
    role VARCHAR(20)
);

CREATE TABLE tblClient (
    code_client VARCHAR(100) PRIMARY KEY,
    client_name VARCHAR(250),
    address VARCHAR(100),
    id_number VARCHAR(250),
    type_id VARCHAR(250),
    phone VARCHAR(20),
    email VARCHAR(20)
);

CREATE TABLE tblBill (
    code_bill VARCHAR(100) PRIMARY KEY,
    bill_date DATE,
    tour_info VARCHAR(250),
    id_user VARCHAR(100),
    code_client VARCHAR(100),
    quantity_ticket INT(10),
    total_price FLOAT(10),
    FOREIGN KEY (id_user) REFERENCES tblUser(code_user),
    FOREIGN KEY (code_client) REFERENCES tblClient(code_client)
);

CREATE TABLE tblTicket (
    code_ticket VARCHAR(100) PRIMARY KEY,
    booking_date DATE,
    note VARCHAR(250),
    id_tour_departure INT(10),
    code_bill VARCHAR(100),
    FOREIGN KEY (id_tour_departure) REFERENCES tblTourDeparture(id),
    FOREIGN KEY (code_bill) REFERENCES tblBill(code_bill)
);

CREATE TABLE tblPenaltyBill (
    code_penalty_bill VARCHAR(100) PRIMARY KEY,
    cancellation_date DATE,
    refund_price FLOAT(10),
    code_bill VARCHAR(100),
    FOREIGN KEY (code_bill) REFERENCES tblBill(code_bill)
);
