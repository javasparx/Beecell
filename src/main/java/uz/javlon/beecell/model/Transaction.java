package uz.javlon.beecell.model;

import javafx.beans.property.SimpleStringProperty;
import uz.javlon.beecell.utils.Utils;

public class Transaction {

    private SimpleStringProperty id;
    private SimpleStringProperty phone;
    private SimpleStringProperty amount;
    private SimpleStringProperty status;
    private SimpleStringProperty providerString;
    private SimpleStringProperty payId;
    private Provider provider;

    public Transaction(String phone, String amount, String payId) {
        this.id = new SimpleStringProperty("0");
        this.payId = new SimpleStringProperty(payId);
        this.phone = new SimpleStringProperty(phone);
        this.amount = new SimpleStringProperty(amount);
        setProvider(phone);
        this.providerString = new SimpleStringProperty(getProvider().name());
        this.status = new SimpleStringProperty(Status.FAILED.name());
    }

    public String getId() {
        return id.get();
    }

    public SimpleStringProperty idProperty() {
        return id;
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public String getPhone() {
        return phone.get();
    }

    public SimpleStringProperty phoneProperty() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
        setProvider(phone);
    }

    public String getAmount() {
        return amount.get();
    }

    public SimpleStringProperty amountProperty() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount.set(amount);
    }

    public String getStatus() {
        return status.get();
    }

    public SimpleStringProperty statusProperty() {
        return status;
    }

    public void setStatus(String status) {
        this.status.set(status);
    }

    public String getProviderString() {
        return providerString.get();
    }

    public SimpleStringProperty providerStringProperty() {
        return providerString;
    }

    public void setProviderString(String providerString) {
        this.providerString.set(providerString);
    }

    public String getPayId() {
        return payId.get();
    }

    public SimpleStringProperty payIdProperty() {
        return payId;
    }

    public void setPayId(String payId) {
        this.payId.set(payId);
    }

    public Provider getProvider() {
        return provider;
    }

    private void setProvider(String phone) {
        if (Utils.isUcell(phone)) {
            this.provider = Provider.UCELL;
        } else {
            this.provider = Provider.BEELINE;
        }
    }
}