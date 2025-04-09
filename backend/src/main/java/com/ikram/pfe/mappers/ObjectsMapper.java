package com.ikram.pfe.mappers;

import com.ikram.pfe.dto.LandDto;
import com.ikram.pfe.dto.RequestDto;
import com.ikram.pfe.dto.SensorDataDto;
import com.ikram.pfe.dto.SensorDto;
import com.ikram.pfe.dto.StationDto;
import com.ikram.pfe.dto.UserDto;
import com.ikram.pfe.models.AppUser;
import com.ikram.pfe.models.Land;
import com.ikram.pfe.models.Request;
import com.ikram.pfe.models.Sensor;
import com.ikram.pfe.models.SensorData;
import com.ikram.pfe.models.Station;
import org.springframework.stereotype.Component;

@Component
public class ObjectsMapper {

  public Request toRequest(RequestDto request) {
    if (request == null) {
      return null;
    }
    return Request.builder()
            .id(request.getId())
            .title(request.getTitle())
            .description(request.getDescription())
            .land(
                    landFromRequest(request)
            )
            .user(
                    AppUser.builder()
                            .id(request.getUserId())
                            .build()
            )
            .build();
  }

  public Land landFromRequest(RequestDto request) {
    return Land.builder()
            .displayName(request.getLandDisplayName())
            .location(request.getLandLocation())
            .user(
                    AppUser.builder()
                            .id(request.getUserId())
                            .build()
            )
            .build();
  }

  public RequestDto fromRequest(Request request) {
    return RequestDto.builder()
            .id(request.getId())
            .title(request.getTitle())
            .description(request.getDescription())
            .status(request.getStatus())
            .landDisplayName(request.getLand().getDisplayName())
            .landLocation(request.getLand().getLocation())
            .userId(request.getUser().getId())
            .userFirstname(request.getUser().getFirstname())
            .userLastname(request.getUser().getLastname())
            .userEmail(request.getUser().getEmail())
            .requestDate(request.getRequestDate())
            .build();
  }

  public SensorDto fromSensor(Sensor sensor) {
    if (sensor == null) {
      return null;
    }
    return SensorDto.builder()
            .id(sensor.getId())
            .sensorName(sensor.getSensorName())
            .type(sensor.getType())
            .build();
  }

  public LandDto fromLand(Land land) {
    if (land == null) {
      return null;
    }
    return LandDto.builder()
            .id(land.getId())
            .displayName(land.getDisplayName())
            .location(land.getLocation())
            .build();
  }

  public StationDto fromStation(Station station) {
    if (station == null) {
      return null;
    }
    return StationDto.builder()
            .id(station.getId())
            .name(station.getName())
            .registrationNumber(station.getRegistrationNumber())
            .build();
  }

  public SensorDataDto fromSensorData(SensorData sensorData) {
    if (sensorData == null) {
      return null;
    }
    return SensorDataDto.builder()
            .id(sensorData.getId())
            .date(sensorData.getDate())
            .solarRadiation(sensorData.getSolarRadiation())
            .windSpeed(sensorData.getWindSpeed())
            .soilHumidity(sensorData.getSoilHumidity())
            .rain(sensorData.getRain())
            .airHumidity(sensorData.getAirHumidity())
            .temperature(sensorData.getTemperature())
            .windDirection(sensorData.getWindDirection())
            .build();

  }

  public UserDto toUserDto(AppUser user) {
    return UserDto.builder()
        .id(user.getId())
        .firstname(user.getFirstname())
        .lastname(user.getLastname())
        .email(user.getEmail())
        .roles(user.getRoles())
        .address(user.getAddress())
        .build();
  }
}
