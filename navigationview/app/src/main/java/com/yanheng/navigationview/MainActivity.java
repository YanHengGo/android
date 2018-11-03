package com.yanheng.navigationview;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private NavigationView navigationView;
    private Menu navigationViewMenu;
    private View navigationViewHeaderView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initNavigation();
    }

    private void initNavigation() {
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if(menuItem.isChecked())menuItem.setChecked(false);
                else menuItem.setChecked(true);

                drawerLayout.closeDrawers();
                switch (menuItem.getItemId()){
                    case R.id.item_home:
                        Toast.makeText(getApplicationContext(),"ホーム",Toast.LENGTH_LONG).show();
                        break;
                    case R.id.item_company:
                        Toast.makeText(getApplicationContext(),"会社について",Toast.LENGTH_LONG).show();
                        break;
                    case R.id.item_help:
                        Toast.makeText(getApplicationContext(),"ヘルプ",Toast.LENGTH_LONG).show();
                        break;
                    case R.id.item_others:
                        Toast.makeText(getApplicationContext(),"そのほか",Toast.LENGTH_LONG).show();
                        break;
                    case R.id.item_qa:
                        Toast.makeText(getApplicationContext(),"お問い合わせ",Toast.LENGTH_LONG).show();
                        break;
                    case R.id.item_vip:
                        Toast.makeText(getApplicationContext(),"会員",Toast.LENGTH_LONG).show();
                        break;
                }
                return false;
            }
        });
        navigationViewMenu = navigationView.getMenu();
        navigationViewHeaderView = navigationView.getHeaderView(0);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.open_draw,
                R.string.close_draw){
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                L.d();
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                L.d();
            }
        };

        drawerLayout.addDrawerListener(actionBarDrawerToggle);

        actionBarDrawerToggle.syncState();
    }
}
