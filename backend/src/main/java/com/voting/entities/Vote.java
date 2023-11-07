package com.voting.entities;

import java.io.Serializable;
import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Vote implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String cpfVoter;

	@Enumerated(EnumType.STRING)
	private VoteMessage voteMessage;

	private Instant moment;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "id_voting_session")
	private Session session;

	public Vote() {
	}

	public Vote(String cpfVoter, VoteMessage voteMessage, Instant moment, Session session) {
		super();
		this.cpfVoter = cpfVoter;
		this.voteMessage = voteMessage;
		this.moment = moment;
		this.session = session;
	}

	public String getCpfVoter() {
		return cpfVoter;
	}

	public void setCpfVoter(String cpfVoter) {
		this.cpfVoter = cpfVoter;
	}

	public VoteMessage getVoteMessage() {
		return voteMessage;
	}

	public void setVoteMessage(VoteMessage voteMessage) {
		this.voteMessage = voteMessage;
	}

	public Instant getMoment() {
		return moment;
	}

	public void setMoment(Instant moment) {
		this.moment = moment;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

}
