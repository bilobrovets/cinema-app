package cinema.dao.impl;

import cinema.dao.AbstractDao;
import cinema.dao.RoleDao;
import cinema.exception.DataProcessingException;
import cinema.model.Role;
import java.util.Optional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDaoImpl extends AbstractDao<Role> implements RoleDao {
    public RoleDaoImpl(SessionFactory factory) {
        super(factory, Role.class);
    }

    @Override
    public Optional<Role> getRoleByName(String roleName) {
        try (Session session = factory.openSession()) {
            Query<Role> getRoleByName = session.createQuery(
                    "FROM Role WHERE name = :name", Role.class);
            getRoleByName.setParameter("name", Role.RoleName.valueOf(roleName));
            return getRoleByName.uniqueResultOptional();
        } catch (Exception e) {
            throw new DataProcessingException("Role with name " + roleName + " not found", e);
        }
    }
}
