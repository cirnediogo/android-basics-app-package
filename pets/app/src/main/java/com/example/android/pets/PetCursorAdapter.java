package com.example.android.pets;

import android.content.Context;
import android.database.Cursor;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.android.pets.data.PetContract.PetEntry;

/**
 * Created by Diogo on 24/06/2017.
 */

public class PetCursorAdapter extends CursorAdapter {

    public PetCursorAdapter(Context context, Cursor c) {
        super(context, c, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.list_item, viewGroup, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        String name = cursor.getString(cursor.getColumnIndex(PetEntry.COLUMN_PET_NAME));
        ((TextView) view.findViewById(R.id.name)).setText(name);
        String breed = cursor.getString(cursor.getColumnIndex(PetEntry.COLUMN_PET_BREED));
        if (TextUtils.isEmpty(breed)) {
            breed = context.getString(R.string.breed_unknown);
        }
        ((TextView) view.findViewById(R.id.breed)).setText(breed);
    }
}
