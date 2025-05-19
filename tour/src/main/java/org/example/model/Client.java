package org.example.model;

public class Client {
    private String codeClient;
    private String clientName;
    private String address;
    private String idNumber;
    private String typeId;
    private String phone;
    private String email;

    public Client() {
        super();
    }

    public Client(String codeClient, String clientName, String address, String idNumber, String typeId, String phone, String email) {
        super();
        this.codeClient = codeClient;
        this.clientName = clientName;
        this.address = address;
        this.idNumber = idNumber;
        this.typeId = typeId;
        this.phone = phone;
        this.email = email;
    }

    public String getCodeClient() {
        return codeClient;
    }

    public void setCodeClient(String codeClient) {
        this.codeClient = codeClient;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
} 