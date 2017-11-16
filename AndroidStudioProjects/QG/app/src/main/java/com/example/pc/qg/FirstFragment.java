package com.example.pc.qg;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Pc on 11/6/2017.
 */

public class FirstFragment extends Fragment implements View.OnClickListener {
    public static final String TITLE = "STOCK";
    private ArrayList<Model> list = new ArrayList<>();
    private RecyclerAdapter adapter;
    private AlertDialog.Builder alertDialog;
    private EditText mName, mQuantity, mPrice;
    private int edit_position;
    private View view;
    public RecyclerView recyclerView;
    private boolean add = false;
    private Paint p = new Paint();

    public static FirstFragment newInstance() {

        return new FirstFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.first_fragment, container, false);
        recyclerView = view.findViewById(R.id.card_recycler_view);
        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(this);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        adapter = new RecyclerAdapter(list, getActivity());
        recyclerView.setAdapter(adapter);
        list.add(new Model("coffee A", "Coffee", 12.3, R.drawable.ic_launcher_background));
        list.add(new Model("Drink B", "Drink", 21, R.drawable.ic_launcher_background));
        list.add(new Model("Meal C", "Meal", 54.3, R.drawable.ic_launcher_background));
        list.add(new Model("Wine D", "Drink", 10, R.drawable.ic_launcher_background));
        list.add(new Model("coffee E", "FastFood", 9.9, R.drawable.ic_launcher_background));
        list.add(new Model("coffee F", "FastFood", 7.1, R.drawable.ic_launcher_background));
        list.add(new Model("coffee G", "FastFood", 1.9, R.drawable.ic_launcher_background));
        list.add(new Model("coffee H", "FastFood", 44, R.drawable.ic_launcher_background));
        list.add(new Model("coffee A", "FastFood", 12.3, R.drawable.ic_launcher_background));
        list.add(new Model("coffee B", "FastFood", 21, R.drawable.blank));
        list.add(new Model("coffee C", "FastFood", 54.3, R.drawable.blank));
        list.add(new Model("coffee D", "FastFood", 10, R.drawable.blank));
        list.add(new Model("coffee E", "FastFood", 9.9, R.drawable.blank));
        list.add(new Model("coffee F", "FastFood", 7.1, R.drawable.blank));
        list.add(new Model("coffee G", "FastFood", 1.9, R.drawable.blank));
        list.add(new Model("coffee H", "FastFood", 44, R.drawable.blank));
        adapter.notifyDataSetChanged();
        initDialog();
        initSwipe();
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Toast.makeText(getActivity(), "you clicked on id :" + position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {
                Toast.makeText(getActivity(), " Long click on id:" + position, Toast.LENGTH_SHORT).show();
            }
        }));
        return view;
    }

    private void initSwipe() {
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();

                if (direction == ItemTouchHelper.LEFT) {
                    adapter.removeItem(position);
                } else {
                    removeView();
                    edit_position = position;
                    alertDialog.setTitle("Edit Stock");
//                    et_country.setText(list.get(position));
                    alertDialog.show();
                }
            }

            @Override
            public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {

                Bitmap icon;
                if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {

                    View itemView = viewHolder.itemView;
                    float height = (float) itemView.getBottom() - (float) itemView.getTop();
                    float width = height / 3;

                    if (dX > 0) {
                        p.setColor(Color.parseColor("#388E3C"));
                        RectF background = new RectF((float) itemView.getLeft(), (float) itemView.getTop(), dX, (float) itemView.getBottom());
                        c.drawRect(background, p);
                        icon = BitmapFactory.decodeResource(getResources(), R.drawable.ic_mode_edit_white_24px);
                        RectF icon_dest = new RectF((float) itemView.getLeft() + width, (float) itemView.getTop() + width, (float) itemView.getLeft() + 2 * width, (float) itemView.getBottom() - width);
                        c.drawBitmap(icon, null, icon_dest, p);
                    } else {
                        p.setColor(Color.parseColor("#D32F2F"));
                        RectF background = new RectF((float) itemView.getRight() + dX, (float) itemView.getTop(), (float) itemView.getRight(), (float) itemView.getBottom());
                        c.drawRect(background, p);
                        icon = BitmapFactory.decodeResource(getResources(), R.drawable.ic_delete_white_24px);
                        RectF icon_dest = new RectF((float) itemView.getRight() - 2 * width, (float) itemView.getTop() + width, (float) itemView.getRight() - width, (float) itemView.getBottom() - width);
                        c.drawBitmap(icon, null, icon_dest, p);
                    }
                }
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    private void removeView() {
        if (view.getParent() != null) {
            ((ViewGroup) view.getParent()).removeView(view);
        }
    }

    private void initDialog() {
        alertDialog = new AlertDialog.Builder(getActivity());
        view = getLayoutInflater().inflate(R.layout.dialog_layout, null);
        final Spinner spinner = view.findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.category, android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
        mName = view.findViewById(R.id.name);
//        mCategory = view.findViewById(R.id.category);

        mPrice = view.findViewById(R.id.price);
        mQuantity = view.findViewById(R.id.quantity);
        alertDialog.setView(view);
        alertDialog.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String mCategory=spinner.getSelectedItem().toString();

                if (add) {
                    add = false;
                    adapter.addItem(new Model(mName.getText().toString(), mCategory, Double.parseDouble(mPrice.getText().toString()), R.drawable.blank));
                    dialog.dismiss();
                } else {
                    list.set(edit_position, new Model(mName.getText().toString(), mCategory, Double.parseDouble(mPrice.getText().toString()), R.drawable.blank));
                    adapter.notifyDataSetChanged();
                    dialog.dismiss();
                }

            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                removeView();
                add = true;
                alertDialog.setTitle("Add item");
//                et_country.setText("");
                alertDialog.show();
                break;

        }
    }

    public static interface ClickListener {
        public void onClick(View view, int position);

        public void onLongClick(View view, int position);
    }

    class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private ClickListener clicklistener;
        private GestureDetector gestureDetector;

        public RecyclerTouchListener(Context context, final RecyclerView recycleView, final ClickListener clicklistener) {

            this.clicklistener = clicklistener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recycleView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clicklistener != null) {
                        clicklistener.onLongClick(child, recycleView.getChildAdapterPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clicklistener != null && gestureDetector.onTouchEvent(e)) {
                clicklistener.onClick(child, rv.getChildAdapterPosition(child));
            }

            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {

        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }


}

