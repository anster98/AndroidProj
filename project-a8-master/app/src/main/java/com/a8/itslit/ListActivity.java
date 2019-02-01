package com.a8.itslit;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity implements RecyclerViewAdapter.ItemClickListener, RecyclerViewAdapter.ItemLongClickListener
{

    RecyclerViewAdapter adapter;

    public static ArrayList<Location> getLocationNames() {
        return locationNames;
    }

    static ArrayList<Location> locationNames = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 0);
        Button returnB = findViewById(R.id.map_btn);
        returnB.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(ListActivity.this, MapsActivity.class));
            }
        });

        try {
            //onTouch(this.getCurrentFocus(), 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Hours hours1;

        hours1 = new Hours("6pm-2am", "6pm-2am", "6pm-2am", "6pm-1am", "6pm-3am", "7pm-3am", "4pm-12am");
        Location loc1 = new Location("Fallout", "Alternative night club, dress code enforced.","117 N 18th St Richmond" ,"N/A", "18", "http://www.falloutrva.com/", hours1.getTodaysHours(),false);
        locationNames.add(loc1);


        hours1 = new Hours("Closed", "4pm-9pm", "4pm-9pm", "4pm-9pm, 10pm-2am", "4pm-9pm, 10pm-2am", "4pm-9pm, 10pm-2am", "12pm-5pm");
        Location loc2 = new Location("Plush RVA", "High-energy restaurant and lounge.", "1708 E Main St Richmond","N/A", "21", "https://www.facebook.com/plushrva/", hours1.getTodaysHours(),false);
        locationNames.add(loc2);

        hours1 = new Hours("Closed", "11am-2am", "11am-2am", "11am-2am", "11am-2am", "5pm-2am", "10am-10pm");
        Location loc3 = new Location("Babe's of Carytown", "LGBTQ bar, daily lunch specials, live bands.", "3166 W Cary St Richmond","N/A", "21", "https://www.facebook.com/babesofcarytown", hours1.getTodaysHours(),false);
        locationNames.add(loc3);

        hours1 = new Hours("6pm-10pm", "6pm-10pm", "6pm-10pm", "6pm-10pm", "6pm-2am", "10pm-2am", "6pm-2am");
        Location loc4 = new Location("The Top", "Restaurant and lounge, live music.", "10 Walnut Alley Richmond","N/A", "21", "https://www.thetoprva.com/", hours1.getTodaysHours(),false);
        locationNames.add(loc4);

        hours1 = new Hours("N/A","N/A","N/A","N/A","N/A","N/A","N/A");
        Location loc5 = new Location("ODC After Hours", "After-hours night club.", "7 E Broad St Richmond","N/A", "21", "https://www.facebook.com/pages/Old-Dominion-Club/192530160764921", hours1.getTodaysHours(),false);
        locationNames.add(loc5);

        hours1 = new Hours("Closed", "Closed", "10pm-2am", "10pm-12am", "12pm-2am", "11am-2pm, 10pm-2am", "11am-4pm");
        Location loc6 = new Location("Godfrey's", "Lively 18+ dance club.", "308 E Grace St Richmond","5", "18", "http://godfreysva.com/", hours1.getTodaysHours(),false);
        locationNames.add(loc6);

        hours1 = new Hours("2pm-2am", "2pm-2am", "2pm-2am", "2pm-2am", "2pm-2am", "2pm-2am", "2pm-2am");
        Location loc7 = new Location("Strange Matter", "Restaurant with retro arcade and live music.", "929 W Grace St Richmond","N/A", "21", "http://www.strangematterrva.com/", hours1.getTodaysHours(),false);
        locationNames.add(loc7);

        hours1 = new Hours("Closed", "5pm-2am", "5pm-10pm", "5pm-10pm", "5pm-2am", "6pm-2am", "11:30am-6pm");
        Location loc8 = new Location("Trifecta Richmond", "Night club featuring weekly guests.", "112 N 5th St Richmond","N/A", "21", "https://www.facebook.com/trifectalounge/", hours1.getTodaysHours(),false);
        locationNames.add(loc8);

        hours1 = new Hours("Closed", "11am-2pm, 4pm-2am", "11am-2pm, 4pm-1am", "11am-2pm, 4pm-2am", "11am-2pm, 4pm-2am", "11am-2pm, 4pm-2am", "Closed");
        Location loc9 = new Location("Image Restaurant and Lounge", "Upscale restaurant with nightlife.", "1713 E Main St Richmond","N/A", "21", "https://www.facebook.com/imagebarrva/", hours1.getTodaysHours(),false);
        locationNames.add(loc9);

        hours1 = new Hours("Closed", "3pm-12am", "3pm-12am", "3pm-1:30am", "3pm-1:30am", "3pm-1:30am", "Closed");
        Location loc10 = new Location("Kabana Rooftop", "Rooftop bar and lounge.", "700 E Main St Richmond","10", "21", "http://www.kabanarooftop.com/", hours1.getTodaysHours(),false);
        locationNames.add(loc10);

        hours1 = new Hours("Closed", "N/A", "N/A", "N/A", "N/A", "N/A", "N/A");
        Location loc11 = new Location("The Canal Club", "Live performance venue in an old, brick warehouse.", "1545 E Cary St Richmond","N/A", "All ages", "https://www.thecanalclub.com/", hours1.getTodaysHours(),false);
        locationNames.add(loc11);

        hours1 = new Hours("11:30am-10pm", "11:30am-2am", "11:30am-2am", "11:30am-2am", "11:30am-2am", "12pm-2am", "12pm-6pm");
        Location loc12 = new Location("Cha Cha's", "Southwestern restaurant and bar known for its late-night scene.", "1419 E Cary St Richmond","N/A", "21", "http://www.chachaslatinkitchen.com/", hours1.getTodaysHours(),false);
        locationNames.add(loc12);

        RecyclerView recyclerView = findViewById(R.id.rvLocations);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(ListActivity.this, DividerItemDecoration.VERTICAL));
        adapter = new RecyclerViewAdapter(this, locationNames);
        adapter.setClickListener(this);
        adapter.setOnLongClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    public void onItemClick(View view, int position)
    {
        Location location = locationNames.get(position);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(location.getURL()));
        startActivity(intent);
    }

    @Override
    public void onItemLongClick(View view, int position) {
        Location location = locationNames.get(position);
        TextView tv = view.findViewById(R.id.tvAnimalName);
        if(location.getFavorite()){
            location.setFavorite(false);
            tv.setBackgroundColor(Color.TRANSPARENT);
            Toast.makeText(getApplicationContext(), location.getName() + " removed from Favorites", Toast.LENGTH_SHORT).show();
        } else if (location.getFavorite() == false){
            location.setFavorite(true);
            tv.setBackgroundColor(Color.parseColor("#F2B32A"));
            Toast.makeText(getApplicationContext(), location.getName() + " added to Favorites", Toast.LENGTH_SHORT).show();
        }
    }
    /*
    public void onTouch(View view, int position) throws Exception //marks a location as favorite and changes the tile color
    {
        Context context = getApplicationContext();


//        //int line =0;
//        Log.d("0000000000", "asdffffffffffffsssssssfdafdsdfsasafdfads");
//        Resources res = context.getResources();
//        InputStream in = res.openRawResource(R.raw.favorites);
//        Scanner scan = new Scanner(in);
//
//        //output
//        File file = new File ("favorites2.txt");
//        file.createNewFile();
//        FileOutputStream fos = new FileOutputStream(file);
//        OutputStreamWriter osw = new OutputStreamWriter (fos);

//        while(scan.hasNext()){
//            Log.d("00000000", scan.next());
//        }
//        int k = 1;
//        for(int i = 0;i<position;i++){
//            String temp = scan.nextLine();
//            //osw.write(temp);
//            k++;
//        }

        // modify the line
//        String line = scan.nextLine();
//        String name = line.replace("[0-9]", "").trim();
        //File favorites = new File("favorites3.txt");


            if (!locationNames.get(position).getFavorite()) // not favorited
            {
                Toast.makeText(getApplicationContext(), "Location favorited", Toast.LENGTH_LONG).show();
                //TODO:change color here
            }
            else //favorited
            {
                Toast.makeText(getApplicationContext(), "Location unfavorited", Toast.LENGTH_LONG).show();
                //TODO: change color here
            }

        //osw.write(name); //write modified club

//        for (int i = k; i<12; i++)//write rest of clubs
//        {
//            String temp = scan.nextLine();
//            osw.write(temp);
//        }
//        file.renameTo(res.openRawResource(R.raw.favorites));
//        newFavorites.renameTo("favorites.txt");

        //change color or add star or whatever
    }
*/
}