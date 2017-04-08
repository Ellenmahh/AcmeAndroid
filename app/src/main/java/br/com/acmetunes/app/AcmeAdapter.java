package br.com.acmetunes.app;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

/**
 * Created by 15251365 on 02/12/2016.
 */
public class AcmeAdapter extends ArrayAdapter <Filmes> {
    int resource;

    public AcmeAdapter(Context context, int resource, Filmes[] objects) {
        super(context, resource, objects);
        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null){

            LayoutInflater inflater = LayoutInflater.from(getContext());
            v = inflater.inflate(resource,null);
        }

        Filmes filme = getItem(position);

        ImageView img_filme = (ImageView) v.findViewById(R.id.img_filme);
        TextView txt_nome = (TextView) v.findViewById(R.id.txt_nome);
        TextView txt_descricao = (TextView) v.findViewById(R.id.txt_descricao);
        TextView txt_categoria = (TextView) v.findViewById(R.id.txt_categoria);
        TextView txt_sub_categora = (TextView) v.findViewById(R.id.txt_sub_categoria_);
        TextView txt_preco = (TextView) v.findViewById(R.id.txt_preco);


        txt_nome.setText(filme.getNome());
        txt_descricao.setText(filme.getDescricao());
        txt_categoria.setText(filme.getCategoria());
        txt_sub_categora.setText(filme.getSub_categoria());
        txt_preco.setText(filme.getPreco() + "");


        if(filme.getImagem() !=null &&  !filme.getImagem().isEmpty()) {

            String caminhoImg =  getContext().getString(R.string.endServidor)+ "/imagem/" + filme.getImagem();
            Log.d("imagem",caminhoImg);
            Picasso.with(getContext()).load(caminhoImg).into(img_filme);
        }


        return v;
    }
}
