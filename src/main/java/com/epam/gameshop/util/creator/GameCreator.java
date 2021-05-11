package com.epam.gameshop.util.creator;

import com.epam.gameshop.entity.Game;
import com.epam.gameshop.entity.SystemRequirements;
import com.epam.gameshop.util.constants.Constants;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class GameCreator implements Creator<Game>, Constants {

    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public Game create(HttpServletRequest request) {
        Game game = new Game();

        game.setName(request.getParameter(NAME_COLUMN));
        game.setCost(Integer.parseInt(request.getParameter(COAST_COLUMN)));
        try {
            game.setReleaseDate(simpleDateFormat.parse(request.getParameter(RELEASE_DATE_COLUMN)));
        } catch (ParseException e) {
            game.setReleaseDate(new Date());
            e.printStackTrace();
        }
        game.setDescription(request.getParameter(DESCRIPTION_COLUMN));
        game.setDeveloper(request.getParameter(DEVELOPER_COLUMN));
        game.setRecommendedSystemRequirements(new SystemRequirements());
        game.setMinimalSystemRequirements(new SystemRequirements());

        game.getRecommendedSystemRequirements().setOperationSystem(request.getParameter(OPERATION_SYS_REC));
        game.getRecommendedSystemRequirements().setCpuName(request.getParameter(CPU_NAME_SYS_REC));
        game.getRecommendedSystemRequirements().setCpuFrequency(Double.parseDouble(request.getParameter(CPU_FREQ_SYS_REC)));
        game.getRecommendedSystemRequirements().setRam(Integer.parseInt(request.getParameter(RAM_SYS_REC)));
        game.getRecommendedSystemRequirements().setVideoAdapterName(request.getParameter(ADAPTER_NAME_SYS_REC));
        game.getRecommendedSystemRequirements().setVideoAdapterMemory(Integer.parseInt(request.getParameter(ADAPTER_MEM_SYS_REC)));
        game.getRecommendedSystemRequirements().setFreeSpace(Integer.parseInt(request.getParameter(FREE_SPACE_SYS_REC)));

        game.getMinimalSystemRequirements().setOperationSystem(request.getParameter(OPERATION_SYS_MIN));
        game.getMinimalSystemRequirements().setCpuName(request.getParameter(CPU_NAME_SYS_MIN));
        game.getMinimalSystemRequirements().setCpuFrequency(Double.parseDouble(request.getParameter(CPU_FREQ_SYS_MIN)));
        game.getMinimalSystemRequirements().setRam(Integer.parseInt(request.getParameter(RAM_SYS_MIN)));
        game.getMinimalSystemRequirements().setVideoAdapterName(request.getParameter(ADAPTER_NAME_SYS_MIN));
        game.getMinimalSystemRequirements().setVideoAdapterMemory(Integer.parseInt(request.getParameter(ADAPTER_MEM_SYS_MIN)));
        game.getMinimalSystemRequirements().setFreeSpace(Integer.parseInt(request.getParameter(FREE_SPACE_SYS_MIN)));

        return game;
    }
}
