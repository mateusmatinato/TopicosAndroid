package com.mateusmatinato.aula03topicos;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class DialogPedido extends DialogFragment {

    public String mensagem;
    public ProgressBar progressBar;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Finalizar Pedido")
                .setMessage(Html.fromHtml(this.mensagem))
                .setPositiveButton("Fechar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        RadioGroup rgSabor = getActivity().findViewById(R.id.rgSabor);
                        rgSabor.clearCheck();

                        CheckBox cbBorda = getActivity().findViewById(R.id.cbBordaRecheada);
                        CheckBox cbBacon = getActivity().findViewById(R.id.cbBacon);
                        CheckBox cbMilho = getActivity().findViewById(R.id.cbMilho);
                        cbBacon.setChecked(false);
                        cbBorda.setChecked(false);
                        cbMilho.setChecked(false);

                        TextView tvPreco = getActivity().findViewById(R.id.tvPreco);
                        tvPreco.setText("R$ 0,00");

                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }

    public void setMensagem(String mensagem){
        this.mensagem = mensagem;
    }
}
