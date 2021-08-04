package br.com.alura.agenda.asynctask;

import android.os.AsyncTask;

import java.util.List;

import br.com.alura.agenda.database.dao.AlunoDAO;
import br.com.alura.agenda.database.dao.TelefoneDAO;
import br.com.alura.agenda.model.Aluno;
import br.com.alura.agenda.model.Telefone;
import br.com.alura.agenda.model.TipoTelefone;

public class EditaAlunoTask extends AsyncTask<Void, Void, Void> {
    private final AlunoDAO alunoDAO;
    private final Aluno aluno;
    private final Telefone telefoneFixo;
    private final Telefone telefoneCelular;
    private final TelefoneDAO telefoneDAO;
    private final List<Telefone> telefonesDoAluno;
    private final AlunoEditadoListener listener;

    public EditaAlunoTask(AlunoDAO alunoDAO, Aluno aluno, Telefone telefoneFixo, Telefone telefoneCelular, TelefoneDAO telefoneDAO, List<Telefone> telefonesDoAluno, AlunoEditadoListener listener) {
        this.alunoDAO = alunoDAO;
        this.aluno = aluno;
        this.telefoneFixo = telefoneFixo;
        this.telefoneCelular = telefoneCelular;
        this.telefoneDAO = telefoneDAO;
        this.telefonesDoAluno = telefonesDoAluno;
        this.listener = listener;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        alunoDAO.edita(aluno);
        vinculaAlunoComTelefone(aluno.getId(), telefoneFixo, telefoneCelular);
        atualizaIdsDosTelefones(telefoneFixo, telefoneCelular);
        telefoneDAO.atualiza(telefoneFixo, telefoneCelular);
        return null;
    }

    @Override
    protected void onPostExecute(Void unused) {
        super.onPostExecute(unused);
        listener.quandoEditado();
    }

    private void vinculaAlunoComTelefone(int alunoId, Telefone... telefones) {
        for (Telefone telefone : telefones) {
            telefone.setAlunoId(alunoId);
        }
    }

    private void atualizaIdsDosTelefones(Telefone telefoneFixo, Telefone telefoneCelular) {
        for (Telefone telefone : telefonesDoAluno) {
            if (telefone.getTipo() == TipoTelefone.FIXO) {
                telefoneFixo.setId(telefone.getId());
            } else {
                telefoneCelular.setId(telefone.getId());
            }
        }
    }

    public interface AlunoEditadoListener {
        void quandoEditado();
    }
}
