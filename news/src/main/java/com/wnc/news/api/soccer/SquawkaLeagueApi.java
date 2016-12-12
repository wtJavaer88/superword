package com.wnc.news.api.soccer;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wnc.basic.BasicNumberUtil;
import com.wnc.basic.BasicStringUtil;
import com.wnc.news.api.common.Club;
import com.wnc.string.PatternUtil;
import com.wnc.utils.JsoupHelper;

public class SquawkaLeagueApi {
	int league_id;
	Logger log = Logger.getLogger(SquawkaLeagueApi.class);

	public SquawkaLeagueApi(int league_id) {
		this.league_id = league_id;
	}

	public List<Club> getAllClubs() {
		List<Club> clubs = new ArrayList<Club>();
		try {
			String jsonStr = JsoupHelper.getJsonResult("http://www.squawka.com/team-directory?ajax=true&league_id="
					+ this.league_id + "&mode=getClubs&cache=" + Math.random());
			if (BasicStringUtil.isNotNullString(jsonStr)) {
				JSONArray arr = JSONArray.parseArray(jsonStr);
				for (int i = 0; i < arr.size(); i++) {
					try {
						Club club = new Club();
						JSONObject jsonObject = (JSONObject) arr.get(i);
						club.setClub_id(BasicNumberUtil.getNumber(jsonObject.getString("club_id")));
						club.setLeague(league_id);
						club.setAbbreviation(jsonObject.getString("abbreviation"));
						club.setShort_name(jsonObject.getString("short_name"));
						club.setPhoto(jsonObject.getString("photo"));
						club.setClub_stats_url(jsonObject.getString("club_stats_url"));
						club.setFull_name(PatternUtil.getFirstPatternGroup(jsonObject.getString("club_stats_url"),
								"teams/(.*?)/stats"));
						clubs.add(club);
					} catch (Exception e) {
						log.info("俱乐部数据解析失败.");
						e.printStackTrace();
					}
				}
			} else {
				log.info("获取联赛俱乐部数据失败!");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return clubs;
	}
}
