package com.challenge.model;

import com.challenge.enums.SessionStatusEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "stave_session")
@Entity
public class StaveSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "duration", nullable = false)
    private Integer duration;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private SessionStatusEnum status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stave_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Stave stave;
    public static class StaveSessionBuilder {
        private static final Integer DEFAULT_DURATION_SESSION_IN_MINUTES = 1;
        private Integer duration;

        public StaveSessionBuilder duration(Integer duration) {
            if (duration != null && duration > 0 && duration < 60)  {
                this.duration = duration;
            } else {
                this.duration = DEFAULT_DURATION_SESSION_IN_MINUTES;
            }

            return this;
        }
    }

}
