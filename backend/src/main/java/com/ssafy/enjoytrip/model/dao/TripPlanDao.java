package com.ssafy.enjoytrip.model.dao;

import com.ssafy.enjoytrip.model.dto.AttractionInfo;
import com.ssafy.enjoytrip.model.dto.TripPlan;
import com.ssafy.enjoytrip.model.dto.TripPlanWithPlaces;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TripPlanDao {

	@Insert("INSERT INTO trip_plan (name, user_id) VALUES (#{name}, #{userId})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	void setTripPlan(TripPlan tripPlan);

	@Select("SELECT tp.id AS tp_id, tp.name AS tp_name, tp.user_id AS tp_user_id, " +
			"ai.content_id AS ai_content_id, ai.content_type_id AS ai_content_type_id, ai.title AS ai_title, ai.addr1 AS ai_addr1, ai.addr2 AS ai_addr2, ai.zipcode AS ai_zipcode, ai.tel AS ai_tel, ai.first_image AS ai_first_image, ai.first_image2 AS ai_first_image2, ai.readcount AS ai_readcount, ai.sido_code AS ai_sido_code, ai.gugun_code AS ai_gugun_code, ai.latitude AS ai_latitude, ai.longitude AS ai_longitude, ai.mlevel AS ai_mlevel " +
			"FROM trip_plan tp " +
			"LEFT JOIN trip_plan_place tpp ON tp.id = tpp.trip_plan_id " +
			"LEFT JOIN attraction_info ai ON tpp.content_id = ai.content_id " +
			"WHERE tp.id = #{id} " +
			"LIMIT 1")
	@Results(id = "TripPlanWithPlacesResultMap", value = {
			@Result(property = "id", column = "tp_id", id = true),
			@Result(property = "name", column = "tp_name"),
			@Result(property = "userId", column = "tp_user_id"),
			@Result(property = "places", column = "tp_id", many = @Many(select = "getAttractionInfoByTripPlanId"))
	})
	TripPlanWithPlaces getTripPlanById(int id);

	@Select("SELECT ai.content_id, ai.content_type_id, ai.title, ai.addr1, ai.addr2, ai.zipcode, ai.tel, ai.first_image, ai.first_image2, ai.readcount, ai.sido_code, ai.gugun_code, ai.latitude, ai.longitude, ai.mlevel " +
			"FROM attraction_info ai " +
			"JOIN trip_plan_place tpp ON ai.content_id = tpp.content_id " +
			"WHERE tpp.trip_plan_id = #{tripPlanId}")
	List<AttractionInfo> getAttractionInfoByTripPlanId(int tripPlanId);

	@Select("SELECT tp.id AS tp_id, tp.name AS tp_name, tp.user_id AS tp_user_id " +
			"FROM trip_plan tp " +
			"WHERE tp.user_id = #{userId}")
	@Results(id = "TripPlanResultMap", value = {
			@Result(property = "id", column = "tp_id", id = true),
			@Result(property = "name", column = "tp_name"),
			@Result(property = "userId", column = "tp_user_id"),
			@Result(property = "places", column = "tp_id", many = @Many(select = "getAttractionInfoByTripPlanId"))
	})
	List<TripPlanWithPlaces> getTripPlansWithPlacesByUserId(String userId);

	@Delete("DELETE FROM trip_plan WHERE id = #{id}")
	void delTripPlan(int id);

	@Update("UPDATE trip_plan SET name = #{name}, user_id = #{userId} WHERE id = #{id}")
	void updateTripPlan(TripPlan tripPlan);

	@Insert("INSERT INTO trip_plan_place (trip_plan_id, content_id) VALUES (#{tripPlanId}, #{contentId})")
	void addPlaceToTripPlan(@Param("tripPlanId") int tripPlanId, @Param("contentId") int contentId);
}
