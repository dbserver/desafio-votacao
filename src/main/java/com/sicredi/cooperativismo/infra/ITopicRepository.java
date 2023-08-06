package com.sicredi.cooperativismo.infra;

import com.sicredi.cooperativismo.domain.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITopicRepository extends JpaRepository<Topic, Long> {

}
