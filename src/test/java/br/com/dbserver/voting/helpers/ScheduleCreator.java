package br.com.dbserver.voting.helpers;

import br.com.dbserver.voting.dtos.ScheduleDTO;
import br.com.dbserver.voting.models.Schedule;

public class ScheduleCreator {
    public static ScheduleDTO createScheduleDtoValid(){
        return new ScheduleDTO(1, "pauta salva");
    }
    public static ScheduleDTO scheduleDTO(){
        return new ScheduleDTO(null, "pauta salva");
    }

    public static ScheduleDTO createScheduleDtoInvalid(){
        return new ScheduleDTO(1, "");
    }

    public  static Schedule scheduleValid(){
        return new Schedule(null, "pauta salva");
    }

    public  static Schedule creatScheduleValid(){
        return new Schedule(null, "pauta salva");
    }
}
