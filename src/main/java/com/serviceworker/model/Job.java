package com.serviceworker.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String jobTitle;
    private String description;
    private double price;
    private String addressLine1;
    private String AddressLine2;
    private String pinCode;
    private LocalDateTime createdAt;
    private String status;


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "job_category",
            joinColumns = @JoinColumn(name = "job_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id"))
    private List<Category> categories;

    @OneToOne(cascade =CascadeType.ALL)
    private PaymentCard paymentCard;

    public Job() {
    }

    public Job(Long id, String username, String jobTitle, String description, double price, String addressLine1, String addressLine2, String pinCode, LocalDateTime createdAt, String status, List<Category> categories, PaymentCard paymentCard) {
        this.id = id;
        this.username = username;
        this.jobTitle = jobTitle;
        this.description = description;
        this.price = price;
        this.addressLine1 = addressLine1;
        AddressLine2 = addressLine2;
        this.pinCode = pinCode;
        this.createdAt = createdAt;
        this.status = status;
        this.categories = categories;
        this.paymentCard = paymentCard;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public PaymentCard getPaymentCard() {
        return paymentCard;
    }

    public void setPaymentCard(PaymentCard paymentCard) {
        this.paymentCard = paymentCard;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return AddressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        AddressLine2 = addressLine2;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Job{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", jobTitle='" + jobTitle + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", addressLine1='" + addressLine1 + '\'' +
                ", AddressLine2='" + AddressLine2 + '\'' +
                ", pinCode='" + pinCode + '\'' +
                ", createdAt=" + createdAt +
                ", status=" + status +
                ", categories=" + categories +
                ", paymentCard=" + paymentCard +
                '}';
    }
}
