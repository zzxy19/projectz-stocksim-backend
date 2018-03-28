package com.projectz.stocksimbackend.showpricetimeline;

import org.springframework.stereotype.Service;


@Service
public class ShowPriceTimelineServiceImpl implements ShowPriceTimelineService {
    @Override
    public ShowPriceTimelineResponse handleShowPriceTimelineRequest(String symbol, int dateRange, int startDate){
        ShowPriceTimelineResponse response = new ShowPriceTimelineResponse();

        return response;
    }

}