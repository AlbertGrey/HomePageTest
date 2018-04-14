package tw.org.iii.homepagetest;



import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedList;



public class AttrPage extends ListFragment {

    private LinkedList<AttrListModel> data;
    private ListView listView;
    private String jstring;
    private JSONObject jsonObject;
    public static String attrtitle="title";
    public static String attraddr="addr";
    public static String attrimage="image";
    private MylistAdapter adapter;
    private float screenWidth,screenHeight,newHeight;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)    {

        View v = inflater.inflate(R.layout.fragment_attr_page,container,false);
        listView=(ListView)v.findViewById(android.R.id.list);
        new attrHttpasync().execute();
        return v;
//        return super.onCreateView(inflater,container,savedInstanceState);
//        return inflater.inflate(R.layout.fragment_hot_page, container, false);
    }
//    private void getScreen(){
//        //螢幕寬高
//        DisplayMetrics metrics = new DisplayMetrics();
//        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
//        screenWidth = metrics.widthPixels;
//        screenHeight = metrics.heightPixels;
//        newHeight = screenWidth / 16 * 9;
//        Log.v("grey","手機寬高1 ＝" + metrics.widthPixels+" X "+metrics.heightPixels);
//    }
    private class attrHttpasync extends AsyncTask<String, Void, LinkedList<AttrListModel>> {

        @Override
        protected LinkedList<AttrListModel> doInBackground(String... strings) {
            JSONArray jsonArray = null;
            data = new LinkedList<>();
            jstring = JSONfuction.getJSONfromurl("http://36.234.13.158:8080/J2EE/getData.jsp?start=1&rows=15");
//            jstring = JSONfuction.getJSONfromurl("http://gis.taiwan.net.tw/XMLReleaseALL_public/scenic_spot_C_f.json");
            Log.v("grey","json1 = "+jstring);
            try {
                jsonArray = new JSONArray(jstring);
//                jsonObject = new JSONObject(jstring);
//                JSONObject j1 =jsonObject.getJSONObject("XML_Head");
//                Log.v("grey","j1="+j1);
//                JSONObject j2 = j1.getJSONObject("Infos");
//                jsonArray = j2.getJSONArray("Info");
                Log.v("grey","jason"+jsonArray);
                for(int i=0;i<jsonArray.length();i++){
                    JSONObject jsonObject2 = jsonArray.getJSONObject(i);
                    JSONArray imgarray = jsonObject2.getJSONArray("imgs");
                    JSONObject jsonObject3 = imgarray.getJSONObject(0);
//                    HashMap<String, Object> map = new HashMap<>();
                    AttrListModel listModel = new AttrListModel();
                    listModel.setName(jsonObject2.getString("stitle"));
//                    listModel.setName(jsonObject2.getString("Name"));
                    listModel.setAddress(jsonObject2.getString("address"));
//                    listModel.setAddress(jsonObject2.getString("Add"));

//                    listModel.setOpentime(jsonObject2.getString("MEMO_TIME"));
//                    listModel.setToldesc(jsonObject2.getString("xbody"));
//                    listModel.setDescription(jsonObject3.getString("description"));
                    listModel.setImgs(jsonObject3.getString("url"));
//                    listModel.setImgs(jsonObject2.getString("Picture1"));
                    data.add(listModel);
//                    map.put("title", jsonObject2.getString("stitle"));
//                    map.put("addr", jsonObject2.getString("address"));
//                    map.put("image", jsonObject2.getString("imgs"));
////                    map.put(from[2],Integer.toString(imgs[1]));
//                    data.add(map);
                }
                Log.v("grey","json = "+jsonArray);
                Log.v("grey","data = "+data);
                return data;
            } catch (Exception e) {
                Log.v("grey","error22 = " + e.toString());
            }
            return null;
        }
        @Override
        protected void onPostExecute(LinkedList jsonresult) {
            super.onPostExecute(jsonresult);
            Log.v("grey","json22 = "+jsonresult);
            adapter = new MylistAdapter(getContext(),data);
            setListAdapter(adapter);
//            SimpleAdapter adapter = new SimpleAdapter(getContext(),data,R.layout.item_layout,from,to);
            Log.v("grey","data=="+data);
//            setListAdapter(adapter);
        }
    }
    public class MylistAdapter extends BaseAdapter {

        private Context context;
        private LayoutInflater inflater;
        private LinkedList<AttrListModel> data;
        private HashMap<String,Object> reslut = new HashMap<>();
        private ViewHolder holder;

        public MylistAdapter(Context context,
                             LinkedList<AttrListModel> linklist) {
            this.context = context;
            this.data = linklist;
            this.inflater = LayoutInflater.from(context);
        }


        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(final int position, View view, ViewGroup viewGroup) {
            holder = new ViewHolder();
            if(view==null){
                inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.item_layout,viewGroup,false);
                holder.itemtitle = (TextView)view.findViewById(R.id.item_title);
                holder.itemaddr = (TextView)view.findViewById(R.id.item_addr);
                holder.itemimage = (ImageView)view.findViewById(R.id.item_image);
            }else{
                holder = (ViewHolder) view.getTag();
            }
            //set reslut to textview
            holder.itemtitle.setText(data.get(position).getName());
            Log.v("grey","holdername = "+data.get(position).getName());
            holder.itemaddr.setText(data.get(position).getAddress());
            Log.v("grey","holderaddr = "+data.get(position).getAddress());
//            new DownloadImage(holder.itemimage).execute(data.get(position).getImgs());
//            GlideApp.with(context)
//                    .load(data.get(position).getImgs())
//                    .into(holder.itemimage);
//                Glide.with(context)
//                        .load("http://www.travel.taipei/d_upload_ttn/sceneadmin/pic/11000340.jpg")
//                        .into(holder.itemimage);
            Log.v("grey","data.image = "+data.get(position).getImgs());
            return view;
        }

        private class ViewHolder{
            public ImageView itemimage;
            public TextView itemaddr;
            public TextView itemtitle;
        }
//        private class DownloadImage extends AsyncTask<String,Void,Bitmap>{
//
//            ImageView bmImage;
//
//            public DownloadImage(ImageView bmImage){
//                this.bmImage = bmImage;
//            }
//            @Override
//            protected Bitmap doInBackground(String... urls) {
//                String urlstring = urls[0];
//                Bitmap bmp =null;
//                Log.v("grey","urlstring = "+urlstring);
//                try {
//                    StringBuilder sbr = new StringBuilder(urlstring);
//                    sbr.insert(4,"s");
//                    Log.v("grey","sb="+sbr);
//                    InputStream in = new URL(sbr.toString()).openStream();
//                    Log.v("grey","in = "+in);
//                    bmp = BitmapFactory.decodeStream(in);
//                    Log.v("grey","bmp = "+bmp);
//
//                    int oWidth = bmp.getWidth();
//                    int oHeight = bmp.getHeight();
//
//                    int newWidth = (int)screenWidth;
//
//                    float scalWidth = (float) newWidth/oWidth;
//                    float scalHeight = newHeight/oHeight;
//                    Matrix matrix = new Matrix();
//                    matrix.postScale(scalWidth,scalHeight);
//                    bmp = Bitmap.createBitmap(bmp,0,0,oWidth,oHeight,matrix,true);
//
//                    return bmp;
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                Log.v("grey","bmp22 = "+bmp);
//                return null;
//            }
//
//            @Override
//            protected void onPostExecute(Bitmap bitmap) {
//                super.onPostExecute(bitmap);
//                bmImage.setImageBitmap(bitmap);
//            }
//        }
    }

}
