package com.example.currencyconvertion.model;

import com.example.currencyconvertion.utils.RequestResponse;
import com.example.currencyconvertion.utils.Status;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "audit_info_currency")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AuditInfoConversion implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int REQUEST_ID;

    @Column(name = "status")
    String status;

    @Column(name = "request")
    String request;

    @Column(name = "response", length = 10000, columnDefinition = "JSON")
    private String response;

    @Column(name = "CREATE_TS")
    String createTs;

    @Column(name = "UPDATE_TS")
    String UPDATE_TS;
}
