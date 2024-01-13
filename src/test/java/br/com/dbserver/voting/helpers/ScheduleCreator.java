package br.com.dbserver.voting.helpers;

import br.com.dbserver.voting.dtos.ScheduleDTO;
import br.com.dbserver.voting.models.Schedule;

import java.util.UUID;

public class ScheduleCreator {
    public static ScheduleDTO createScheduleDtoValid(){
        return new ScheduleDTO(UUID.fromString("d6df5158-cd61-48f3-a8cb-0660c24d1a23"), "pauta salva");
    }
    public static ScheduleDTO scheduleDTO(){
        return new ScheduleDTO(null, "pauta salva");
    }

    public static ScheduleDTO createScheduleDtoInvalid(){
        return new ScheduleDTO(UUID.fromString("d6df5158-cd61-48f3-a8cb-0660c24d1a23"), "");
    }

    public  static Schedule scheduleValid(){
        return new Schedule(UUID.fromString("d6df5158-cd61-48f3-a8cb-0660c24d1a23"), "pauta salva");
    }

    public  static Schedule creatScheduleValid(){
        return new Schedule(null, "pauta salva");
    }
}
