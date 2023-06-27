package com.example.PI_Emi_Tania.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
@Entity
public class Turno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id", nullable = false)
    @NotNull(message = "El paciente no puede ser nulo")
    private Paciente paciente;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "odontologo_id", nullable = false)
    @NotNull(message = "El odontologo no puede ser nulo")
    private Odontologo odontologo;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    @FutureOrPresent(message = "La fecha no puede ser anterior al día de hoy")
    @NotNull(message = "Debe especificarse la fecha y hora del turno")
    private LocalDateTime fechaYHora;

    public Turno(Long id, Paciente paciente, Odontologo odontologo, LocalDateTime fechaYHora) {
        this.id = id;
        this.paciente = paciente;
        this.odontologo = odontologo;
        this.fechaYHora = fechaYHora;
    }

    public Turno(Paciente paciente, Odontologo odontologo, LocalDateTime fechaYHora) {
        this.paciente = paciente;
        this.odontologo = odontologo;
        this.fechaYHora = fechaYHora;
    }

    public Turno() {
    }

    public Long getId() {
        return id;
    }


    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Odontologo getOdontologo() {
        return odontologo;
    }

    public void setOdontologo(Odontologo odontologo) {
        this.odontologo = odontologo;
    }

    public LocalDateTime getFechaYHora() {
        return fechaYHora;
    }

    public void setFechaYHora(LocalDateTime fechaYHora) {
        this.fechaYHora = fechaYHora;
    }

    @Override
    public String toString() {
        return "Turno" +
                "id" + id +
                ", paciente=" + paciente +
                ", odontologo=" + odontologo +
                ", fechaYHora=" + fechaYHora;
    }
}
