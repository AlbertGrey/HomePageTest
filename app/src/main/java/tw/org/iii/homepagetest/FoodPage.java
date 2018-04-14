package tw.org.iii.homepagetest;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FoodPage extends ListFragment {

    private LinkedList<HashMap<String,Object>> data;
    private String[] from={"title","cont","image"};
    private int[] to={R.id.item_title,R.id.item_addr,R.id.item_image};
    private int[] imgs={R.drawable.a1,R.drawable.a2};


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)    {

        initdata();
        SimpleAdapter adapter = new SimpleAdapter(getActivity(),data,R.layout.item_layout,from,to);
        setListAdapter(adapter);

        return super.onCreateView(inflater,container,savedInstanceState);
//        return inflater.inflate(R.layout.fragment_hot_page, container, false);
    }

    private class ListviewAdapter extends BaseAdapter{

        private Context context;
        private LayoutInflater inflater;
        private ArrayList<HashMap<String, String>> data;
        private HashMap<String, String> resultp = new HashMap<String, String>();

        @Override
        public int getCount() {
            return 0;
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
        public View getView(int i, View view, ViewGroup viewGroup) {
            return null;
        }
    }

    private void initdata(){
        data = new LinkedList<>();
        HashMap<String,Object> map0 = new HashMap<>();
        map0.put(from[0],"Hello");
        map0.put(from[1],"grey");
        map0.put(from[2],Integer.toString(imgs[0]));
        data.add(map0);

        HashMap<String,Object>map1 = new HashMap<>();
        map1.put(from[0],"Qoo");
        map1.put(from[1],"yyy");
        map1.put(from[2],Integer.toString(imgs[1]));
        data.add(map1);

        HashMap<String,Object>map2 = new HashMap<>();
        map2.put(from[0],"Small");
        map2.put(from[1],"chick");
        map2.put(from[2],Integer.toString(imgs[1]));
        data.add(map2);
    }

}
