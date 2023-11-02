package com.voting.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Session implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private Instant openingDate;
	private Instant closingDate;

	@OneToOne
	@JoinColumn(name = "id")
	private Topic topic;

	public Session() {
	}

	public Session(Instant openingDate, Instant closingDate, Topic topic) {
		super();
		this.openingDate = openingDate;
		this.closingDate = closingDate;
		this.topic = topic;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Instant getOpeningDate() {
		return openingDate;
	}

	public void setOpeningDate(Instant openingDate) {
		this.openingDate = openingDate;
	}

	public Instant getClosingDate() {
		return closingDate;
	}

	public void setClosingDate(Instant closingDate) {
		this.closingDate = closingDate;
	}

	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Session other = (Session) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Session [id=" + id + ", openingDate=" + openingDate + ", closingDate=" + closingDate + ", topic="
				+ topic + "]";
	}

}
