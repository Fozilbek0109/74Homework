package uz.coder.a74homework.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import uz.coder.a74homework.databinding.ItemImgBinding;
import uz.coder.a74homework.model.ImgModel;

public class ImgAdapter extends RecyclerView.Adapter<ImgAdapter.VH>{

    private List<ImgModel> imgModelList;

    public ImgAdapter(List<ImgModel> imgModelList) {
        this.imgModelList = imgModelList;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VH(ItemImgBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        Picasso.get().load(imgModelList.get(position).getThumbnailUrl()).into(holder.binding.img);
        holder.binding.txt.setText(imgModelList.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return imgModelList.size();
    }

    public static class VH extends RecyclerView.ViewHolder {
        ItemImgBinding binding;
        public VH(@NonNull  ItemImgBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }
}
