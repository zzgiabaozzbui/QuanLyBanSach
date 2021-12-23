/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.util.List;

public interface Dao<T> {
    String insert(T t);
    boolean update(T t);
    boolean delete(T t);
    List<T> findByMa(String ma);
}
