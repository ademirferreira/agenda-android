package br.com.alura.agenda.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import br.com.alura.agenda.model.Telefone;

@Dao
public interface TelefoneDAO {
    @Query("SELECT * FROM Telefone " +
            "WHERE alunoId = :alunoId LIMIT 1")
    Telefone buscaPrimeiroTelefoneDoAluno(int alunoId);

    @Insert
    void salva(Telefone... telefones);
}
