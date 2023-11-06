package com.voting.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Transient;

@Entity
public class Session implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private Instant openingDate;
	private Instant closingDate;

	@Transient
	private Map<String, Long> result;

	@OneToOne
	@JoinColumn(name = "id_topic")
	private Topic topic;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "session", cascade = CascadeType.ALL)
	private Set<Vote> votes = new HashSet<Vote>();

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

	public Map<String, Long> getResult() {
		return result;
	}

	public void setResult(Map<String, Long> result) {
		this.result = result;
	}

	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}

	public Set<Vote> getVotes() {
		return votes;
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
