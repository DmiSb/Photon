package ru.dmisb.photon.data.network.adapters;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.ToJson;

import java.text.ParseException;
import java.util.Date;

import ru.dmisb.photon.utils.FormatUtils;

@SuppressWarnings("unused")
public class DateAdapter {

    @FromJson
    Date dateFromJson(String value) {
        try {
            return FormatUtils.getResFormat().parse(value);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    @ToJson
    String dateToJson(Date date) {
        return FormatUtils.resFormatDate(date);
    }
}
