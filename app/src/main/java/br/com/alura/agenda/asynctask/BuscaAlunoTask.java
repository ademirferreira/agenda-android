package br.com.alura.agenda.asynctask;

import android.os.AsyncTask;

import java.util.List;

import br.com.alura.agenda.database.dao.AlunoDAO;
import br.com.alura.agenda.model.Aluno;
import br.com.alura.agenda.ui.adapter.ListaAlunosAdapter;

public class BuscaAlunoTask extends AsyncTask<Void, Void, List<Aluno>> {
    private final AlunoDAO dao;
    private final ListaAlunosAdapter adapter;

    public BuscaAlunoTask(AlunoDAO dao, ListaAlunosAdapter adapter) {
        this.dao = dao;
        this.adapter = adapter;
    }

    @Override
    protected List<Aluno> doInBackground(Void[] objects) {
        return dao.todos();
    }

    @Override
    protected void onPostExecute(List<Aluno> todosAlunos) {
        super.onPostExecute(todosAlunos);
        adapter.atualiza(todosAlunos);
    }
}
