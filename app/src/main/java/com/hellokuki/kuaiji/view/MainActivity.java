package com.hellokuki.kuaiji.view;

import android.animation.ObjectAnimator;
import android.graphics.Rect;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.hellokuki.kuaiji.R;
import com.hellokuki.kuaiji.adapter.MainRecyclerViewAdapter;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private CollapsingToolbarLayout mCollapsingToolbarLayout;
    private AppBarLayout mAppBarLayout;
    private RelativeLayout mRelativeLayout;
    private RecyclerView mRecyclerView;
    private FloatingActionButton mButtonAdd;

    private MainRecyclerViewAdapter mRecyclerAdapter;
    private ObjectAnimator mAnimatorShow;
    private ObjectAnimator mAnimatorHide;
    private ObjectAnimator mAnimatorButtonShow;
    private ObjectAnimator mAnimatorButtonHide;
    private boolean mTitleIsShow = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mAppBarLayout = (AppBarLayout) findViewById(R.id.app_bar_layout);
        mCollapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar_layout);
        mRelativeLayout = (RelativeLayout) findViewById(R.id.relative_layout_title_content);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mButtonAdd= (FloatingActionButton) findViewById(R.id.button_add);


        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mRecyclerAdapter = new MainRecyclerViewAdapter(this);
        mRecyclerView.addItemDecoration(new SpaceItemDecoration(8));
        mRecyclerAdapter.addFooterView(LayoutInflater.from(this).inflate(R.layout.layout_bill_item_footer, mRecyclerView, false));
        mRecyclerView.setAdapter(mRecyclerAdapter);

        mAppBarLayout.addOnOffsetChangedListener(new OnOffsetListener());
        mRelativeLayout.setAlpha(0f);
        mAnimatorShow = ObjectAnimator.ofFloat(mRelativeLayout, "alpha", 0f, 1f).setDuration(500);
        mAnimatorHide = ObjectAnimator.ofFloat(mRelativeLayout, "alpha", 1f, 0f).setDuration(500);
        mAnimatorButtonShow = ObjectAnimator.ofFloat(mButtonAdd, "alpha", 0f, 1f).setDuration(500);
        mAnimatorButtonHide = ObjectAnimator.ofFloat(mButtonAdd, "alpha", 1f, 0f).setDuration(500);
    }

    class OnOffsetListener implements AppBarLayout.OnOffsetChangedListener {
        @Override
        public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
            if (!mTitleIsShow && Math.abs(verticalOffset) > appBarLayout.getTotalScrollRange() / 1.63) {
                mAnimatorShow.start();
                mAnimatorButtonHide.start();
                mTitleIsShow = true;
            }
            if (mTitleIsShow && Math.abs(verticalOffset) < appBarLayout.getTotalScrollRange() / 1.63) {
                mAnimatorHide.start();
                mAnimatorButtonShow.start();
                mTitleIsShow = false;
            }

        }
    }

    public class SpaceItemDecoration extends RecyclerView.ItemDecoration {

        private int space;

        public SpaceItemDecoration(int space) {
            this.space = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            if (parent.getChildPosition(view) != 0) {
                outRect.top = space;
            } else {
                outRect.top = space * 3;
            }
        }
    }


}
