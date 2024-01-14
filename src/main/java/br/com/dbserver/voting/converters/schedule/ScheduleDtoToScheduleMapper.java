package br.com.dbserver.voting.converters.schedule;

import br.com.dbserver.voting.converters.Mapper;
import br.com.dbserver.voting.dtos.ScheduleDTO;
import br.com.dbserver.voting.models.Schedule;
import org.springframework.stereotype.Component;

@Component
public class ScheduleDtoToScheduleMapper implements Mapper<ScheduleDTO, Schedule> {
    @Override
    public Schedule map(ScheduleDTO scheduleDTO, Schedule schedule) {
        return new Schedule(scheduleDTO.id(), scheduleDTO.title());
    }
}
