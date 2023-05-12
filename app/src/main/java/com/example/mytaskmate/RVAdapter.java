package com.example.mytaskmate;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mytaskmate.databinding.EachRvBinding;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class RVAdapter extends ListAdapter<Note, RVAdapter.ViewHolder > {

    public RVAdapter(MainActivity mainActivity)
    {
        super(CALLBACK);
    }

    private static final DiffUtil.ItemCallback<Note> CALLBACK = new DiffUtil.ItemCallback<Note>() {
        @Override
        public boolean areItemsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return oldItem.getId()==newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return oldItem.getTitle().equals(newItem.getTitle())
                    && oldItem.getDesc().equals(newItem.getDesc());
        }
    };

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.each_rv, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Note note = getItem(position);
        holder.binding.titleRv.setText(note.getTitle());
        holder.binding.descRv.setText(note.getDesc());
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        holder.binding.dateTV.setText(dateFormat.format(note.getDate()));
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        holder.binding.popDate.setText(timeFormat.format(note.getTime()));

        // Get the current date
        Date currentDate = Calendar.getInstance().getTime();

        // Compare the note date with the current date
        GradientDrawable shape = new GradientDrawable();
        shape.setShape(GradientDrawable.RECTANGLE);

        shape.setCornerRadii(new float[]{40, 40, 40, 40, 40, 40, 40, 40}); // Set the radius here

        if (note.getDate().before(currentDate)) {
            shape.setColor(Color.parseColor("#FEA1A1"));
            holder.binding.status.setText(R.string.overdue);
        } else if (note.getDate().equals(currentDate)) {
            shape.setColor(Color.parseColor("#A4D0A4"));
            holder.binding.status.setText(R.string.today);
        } else {
            shape.setColor(Color.parseColor("#8DA7F8"));
            holder.binding.status.setText(R.string.ongoing);
        }
        holder.binding.colorCNG.setBackground(shape);
    }

    public Note getNote(int position)
    {
       return getItem(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        EachRvBinding binding;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = EachRvBinding.bind(itemView);
        }
    }
}
