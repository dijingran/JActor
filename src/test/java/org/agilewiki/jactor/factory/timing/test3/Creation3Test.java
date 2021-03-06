package org.agilewiki.jactor.factory.timing.test3;

import junit.framework.TestCase;
import org.agilewiki.jactor.JAMailboxFactory;
import org.agilewiki.jactor.MailboxFactory;
import org.agilewiki.jactor.factory.JAFactory;
import org.agilewiki.jactor.factory.timing.A;

public class Creation3Test extends TestCase {
    public void test() {

        long c = 1;

        //System.out.println("####################################################");
        //long c = 10000000;
        //iterations per second = 49,261,083

        MailboxFactory mailboxFactory = JAMailboxFactory.newMailboxFactory(1);
        try {
            JAFactory f = new JAFactory();
            f.initialize(mailboxFactory.createMailbox());
            f.defineActorType("A", A.class);
            loop(c, f);
            loop(c, f);
            long t0 = System.currentTimeMillis();
            loop(c, f);
            long t1 = System.currentTimeMillis();
            long d = t1 - t0;
            if (d > 0)
                System.out.println(1000 * c / d);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mailboxFactory.close();
        }
    }

    void loop(long c, JAFactory f) throws Exception {
        int i = 0;
        while (i < c) {
            JAFactory.newActor(f, "A");
            i += 1;
        }
    }
}
