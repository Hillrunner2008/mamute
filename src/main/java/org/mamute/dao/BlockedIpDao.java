package org.mamute.dao;

import java.util.List;
import javax.inject.Inject;
import org.hibernate.Session;
import org.mamute.model.ban.BlockedIp;

public class BlockedIpDao {

    @Inject
    private Session session;

    public void save(BlockedIp blockedIp) {
        session.save(blockedIp);
    }

    public List<BlockedIp> list() {
        return session.createQuery("from BlockedIp")
                .setCacheable(true).list();
    }

    public BlockedIp find(Long id) {
        BlockedIp blocked = (BlockedIp) session.load(BlockedIp.class, id);
        return blocked;
    }

    public int delete(Long id) {
        return session.createQuery("delete BlockedIp b where id=:id")
                .setParameter("id", id)
                .executeUpdate();
    }
}
