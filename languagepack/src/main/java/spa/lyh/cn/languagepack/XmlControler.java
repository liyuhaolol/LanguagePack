package spa.lyh.cn.languagepack;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.util.Log;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import spa.lyh.cn.languagepack.model.LanguageInfo;

public class XmlControler {
    public static List<LanguageInfo> getLanguageList(Context context){
        AssetManager am = context.getResources().getAssets();
        XmlPullParser parser = Xml.newPullParser();
        List<LanguageInfo> list = new ArrayList<>();
        try {
            InputStream is = am.open("Locales.xml");
            parser.setInput(is, "UTF-8");
            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType){
                    case XmlPullParser.START_DOCUMENT:
                        break;
                    case XmlPullParser.START_TAG:
                        String name = parser.getName();
                        if (name.equals("item")){
                            LanguageInfo info = new LanguageInfo();
                            info.name = parser.getAttributeValue(null, "name");
                            info.language = parser.getAttributeValue(null, "language");
                            info.country = parser.getAttributeValue(null, "country");
                            if (TextUtils.isEmpty(info.country)){
                                info.country = "";
                            }
                            info.content = parser.nextText();
                            list.add(info);
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        break;
                }
                eventType = parser.next();
            }
            is.close();
        } catch (FileNotFoundException e){
            Log.e("XmlControler","Can't found Locales.xml in assets folder!");
        }catch (XmlPullParserException | IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}
