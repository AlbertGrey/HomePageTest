package tw.org.iii.homepagetest;



import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedList;


public class HotPage extends ListFragment {

    private LinkedList<HashMap<String,String>> data;
    private String[] from={"name","cont","image"};
    private int[] to={R.id.item_title,R.id.item_addr,R.id.item_image};
    private int[] imgs={R.drawable.a1,R.drawable.a2};
    private ProgressDialog pDialog;
    private JSONObject jsonObject;
    private ListView listView;
    private String jstring;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)    {
        View v = inflater.inflate(R.layout.fragment_hot_page,container,false);
        listView = (ListView)v.findViewById(android.R.id.list);
        new Httpasync().execute();
        return v;
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    private class Httpasync extends AsyncTask<String, Void, LinkedList<HashMap<String,String>>> {


        @Override
        protected LinkedList<HashMap<String,String>> doInBackground(String... strings) {
            String result = null;
            String jsonstring = null;
            StringBuffer sb = new StringBuffer();
            JSONArray jsonArray = null;
            data = new LinkedList<>();
//            jstring = JSONfuction.getJSONfromurl("http://gis.taiwan.net.tw/XMLReleaseALL_public/scenic_spot_C_f.json");
            jstring = JSONfuction.getJSONfromurl("http://36.234.13.158:8080/J2EE/getData.jsp?start=1&rows=10");
//            try {
//                URL url = new URL("http://gis.taiwan.net.tw/XMLReleaseALL_public/scenic_spot_C_f.json");
//                HttpURLConnection conn = (HttpURLConnection)url.openConnection();
//                conn.connect();
//
//                BufferedReader breader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//                Log.v("grey", "reader = " + breader);
//                while ((jsonstring = breader.readLine()) != null) {
//                    sb.append(jsonstring);
//                }
//                Log.v("grey", "sb= " + sb);
//                breader.close();
//                result = sb.toString();
//                Log.v("grey","result = "+result);
////                return result;
//            } catch (Exception e) {
//                Log.v("grey", "error = " + e.toString());
//            }
//            Log.v("grey", "return = " + sb.toString());
            Log.v("grey","jsonh1 = "+jstring);
            try {
                jsonArray = new JSONArray(jstring);
//                jsonObject = new JSONObject(jstring);
//                JSONObject j1 =jsonObject.getJSONObject("XML_Head");
//                Log.v("grey","j1="+j1);
//                JSONObject j2 = j1.getJSONObject("Infos");
//                jsonArray = j2.getJSONArray("Info");
                Log.v("grey","jasonh"+jsonArray);

                        for(int i=0;i<jsonArray.length();i++){
                            JSONObject jsonObject2 = jsonArray.getJSONObject(i);
                                HashMap<String, String> map = new HashMap<>();
                                map.put(from[0], jsonObject2.getString("stitle"));
                                map.put(from[1], jsonObject2.getString("address"));
//                                map.put(from[2],jsonObject2.getString("Picture1"));
//                    map.put(from[2],Integer.toString(imgs[1]));
                                data.add(map);
                            Log.v("grey","datah = "+data);
                        }

                Log.v("grey","jsonh = "+jsonArray);
//                jsonArray = new JSONArray(jstring);
                return data;
            } catch (Exception e) {
                Log.v("grey","errorh22 = " + e.toString());
            }
            return null;
        }
        @Override
        protected void onPostExecute(LinkedList<HashMap<String,String>> jsonresult) {
            super.onPostExecute(jsonresult);
            Log.v("grey","jsonh22 = "+jsonresult);
//            try {
//                JSONArray jsonArray = new JSONArray(json);
//                Log.v("grey","json = "+jsonArray);
//                for(int i=0;i<jsonArray.length();i++){
//                    JSONObject jsonObject = jsonArray.getJSONObject(i);
//                    HashMap<String,Object> map = new HashMap<>();
//                    map.put(from[0],jsonObject.getString("Name"));
//                    map.put(from[1],jsonObject.getString("Add"));
//                    map.put(from[2],Integer.toString(imgs[1]));
////                    map.put(from[2],jsonObject.getString("Picture1"));
//                    data.add(map);
//                    Log.v("grey","data = "+data);
//                }
//            } catch (Exception e) {
//                Log.v("grey","error22 = " + e.toString());
//            }
            SimpleAdapter adapter = new SimpleAdapter(getContext(),data,R.layout.item_layout,from,to);
            Log.v("grey","datah=="+data);
            setListAdapter(adapter);
//            if(pDialog!=null){
//                pDialog.dismiss();
//            }
        }
    }
//    @Override
//    public void onListItemClick(ListView l, View v, int position, long id) {
//        super.onListItemClick(l, v, position, id);
//        HashMap<String,String> aData = data.get(position);
//
//        Intent intent = new Intent(getActivity(),DetailActivity.class);
//        intent.putExtra("name",aData.get("name"));
//        intent.putExtra("addr",aData.get("addr"));
//        intent.putExtra("tel",aData.get("tel"));
//        intent.putExtra("toldesc",aData.get("toldesc"));
//        intent.putExtra("image",aData.get("image"));
//        startActivity(intent);
//    }
//
//    private void getJSONData() {
//        new Thread(){
//            @Override
//            public void run() {
//                try {
//                    URL url = new URL("http://gis.taiwan.net.tw/XMLReleaseALL_public/scenic_spot_C_f.json");
//                    HttpURLConnection conn = (HttpURLConnection)url.openConnection();
//                    conn.connect();
//                    conn.setRequestMethod("GET");
//                    Log.v("grey","conn = "+ conn);
//
//                    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//                    StringBuffer sb = new StringBuffer();
//                    Log.v("grey","reader = "+reader);
//                    String line= null;
//                    Log.v("grey","here");
//                    while ((line=reader.readLine()) != null){
//                        sb.append(line+"\n");
//                    }
//                    Log.v("grey","line="+line);
//                    reader.close();
////                    conn.disconnect();
//                    Log.v("grey","sb = "+sb);
//                    parseJSONData(sb.toString());
//                } catch (Exception e) {
//                    Log.v("grey","error =");
//                }
//            }
//        }.start();
//    }
//
//    void parseJSONData(String json){
//        data = new LinkedList<>();
//        try {
//            JSONArray jsonArray = new JSONArray(json);
//            for(int i=0;i<jsonArray.length();i++){
//                JSONObject jsonObject = jsonArray.getJSONObject(i);
//
//                HashMap<String,String> row = new HashMap<>();
//                row.put(from[0],jsonObject.getString("Name"));
//                row.put(from[1],jsonObject.getString("Description"));
//                row.put(from[2],jsonObject.getString("Picture1"));
//                row.put("addr",jsonObject.getString("Add"));
//                row.put("tel",jsonObject.getString("Tel"));
//                row.put("toldesc",jsonObject.getString("Toldescription"));
//
//
//                data.add(row);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        SimpleAdapter adapter = new SimpleAdapter(getActivity(),data,R.layout.item_layout,from,to);
//        setListAdapter(adapter);
//    }


//    private void initdata(){
//        data = new LinkedList<>();
//        HashMap<String,Object> map0 = new HashMap<>();
//        map0.put(from[0],"BIG");
//        map0.put(from[1],"apple");
//        map0.put(from[2],Integer.toString(imgs[1]));
//        data.add(map0);
//
//        HashMap<String,Object>map1 = new HashMap<>();
//        map1.put(from[0],"Qoo");
//        map1.put(from[1],"yyy");
//        map1.put(from[2],Integer.toString(imgs[0]));
//        data.add(map1);
//    }


}