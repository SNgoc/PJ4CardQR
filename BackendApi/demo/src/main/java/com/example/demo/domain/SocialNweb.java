package com.example.demo.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Setter
@Getter
@Table(name = "socialweb", uniqueConstraints = { @UniqueConstraint(columnNames = "user_id") })
public class SocialNweb {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long social_id;
    @Column
    private String facebook;
    @Column
    private String twitter;
    @Column
    private String instagram;
    @Column
    private String tiktok;
    @Column
    private String web1;
    @Column
    private String web2;
    @Column
    private String company1;
    @Column
    private String company2;
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")//tham chieu toi cot id cua tb user
    private User user;

    public SocialNweb() {
    }

    public SocialNweb(String facebook, String twitter, String instagram, String tiktok, String web1, String web2, String company1, String company2) {
        this.facebook = facebook;
        this.twitter = twitter;
        this.instagram = instagram;
        this.tiktok = tiktok;
        this.web1 = web1;
        this.web2 = web2;
        this.company1 = company1;
        this.company2 = company2;
    }

    public SocialNweb(String facebook, String twitter, String instagram, String tiktok, String web1, String web2, String company1, String company2, User user) {
        this.facebook = facebook;
        this.twitter = twitter;
        this.instagram = instagram;
        this.tiktok = tiktok;
        this.web1 = web1;
        this.web2 = web2;
        this.company1 = company1;
        this.company2 = company2;
        this.user = user;
    }
}
