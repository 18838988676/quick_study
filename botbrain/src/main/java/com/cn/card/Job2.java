package com.cn.card;

import java.util.concurrent.Callable;

public class Job2 implements Callable {

    private  int num=100;
    String st=new String("");
    /**
     * Computes a result, or throws an exception if unable to do so.
     *
     * @return computed result
     * @throws Exception if unable to compute a result
     */
    @Override
    public Object call() throws Exception {
       StringBuffer msg=new StringBuffer();
        while (true) {
            if (num > 0) {
                System.out.println("线程：" + Thread.currentThread().getName() + ",剩余：" + num);
                num--;
                msg.append("msg线程：" + Thread.currentThread().getName() + ",剩余：" + num + "\t");
                Thread.sleep(10);
                return msg;
            }
        }

    }
}
//msg线程：我是线程1,剩余：99	msg线程：我是线程1,剩余：93	msg线程：我是线程1,剩余：88	msg线程：我是线程1,剩余：84	msg线程：我是线程1,剩余：79	msg线程：我是线程1,剩余：74	msg线程：我是线程1,剩余：68	msg线程：我是线程1,剩余：64	msg线程：我是线程1,剩余：57	msg线程：我是线程1,剩余：54	msg线程：我是线程1,剩余：49	msg线程：我是线程1,剩余：44	msg线程：我是线程1,剩余：39	msg线程：我是线程1,剩余：34	msg线程：我是线程1,剩余：29	msg线程：我是线程1,剩余：24	msg线程：我是线程1,剩余：18	msg线程：我是线程1,剩余：14	msg线程：我是线程1,剩余：8	msg线程：我是线程1,剩余：4
//msg线程：我是线程2,剩余：96	msg线程：我是线程2,剩余：91	msg线程：我是线程2,剩余：86	msg线程：我是线程2,剩余：81	msg线程：我是线程2,剩余：76	msg线程：我是线程2,剩余：70	msg线程：我是线程2,剩余：66	msg线程：我是线程2,剩余：60	msg线程：我是线程2,剩余：56	msg线程：我是线程2,剩余：51	msg线程：我是线程2,剩余：45	msg线程：我是线程2,剩余：41	msg线程：我是线程2,剩余：36	msg线程：我是线程2,剩余：30	msg线程：我是线程2,剩余：26	msg线程：我是线程2,剩余：21	msg线程：我是线程2,剩余：16	msg线程：我是线程2,剩余：11	msg线程：我是线程2,剩余：6	msg线程：我是线程2,剩余：0
//msg线程：我是线程3,剩余：97	msg线程：我是线程3,剩余：94	msg线程：我是线程3,剩余：87	msg线程：我是线程3,剩余：83	msg线程：我是线程3,剩余：78	msg线程：我是线程3,剩余：73	msg线程：我是线程3,剩余：69	msg线程：我是线程3,剩余：63	msg线程：我是线程3,剩余：58	msg线程：我是线程3,剩余：53	msg线程：我是线程3,剩余：47	msg线程：我是线程3,剩余：42	msg线程：我是线程3,剩余：37	msg线程：我是线程3,剩余：32	msg线程：我是线程3,剩余：25	msg线程：我是线程3,剩余：22	msg线程：我是线程3,剩余：17	msg线程：我是线程3,剩余：12	msg线程：我是线程3,剩余：5	msg线程：我是线程3,剩余：1
//msg线程：我是线程4,剩余：98	msg线程：我是线程4,剩余：92	msg线程：我是线程4,剩余：89	msg线程：我是线程4,剩余：82	msg线程：我是线程4,剩余：77	msg线程：我是线程4,剩余：72	msg线程：我是线程4,剩余：67	msg线程：我是线程4,剩余：62	msg线程：我是线程4,剩余：59	msg线程：我是线程4,剩余：52	msg线程：我是线程4,剩余：48	msg线程：我是线程4,剩余：43	msg线程：我是线程4,剩余：38	msg线程：我是线程4,剩余：33	msg线程：我是线程4,剩余：28	msg线程：我是线程4,剩余：23	msg线程：我是线程4,剩余：19	msg线程：我是线程4,剩余：13	msg线程：我是线程4,剩余：9	msg线程：我是线程4,剩余：3
//msg线程：我是线程5,剩余：95	msg线程：我是线程5,剩余：90	msg线程：我是线程5,剩余：85	msg线程：我是线程5,剩余：80	msg线程：我是线程5,剩余：75	msg线程：我是线程5,剩余：71	msg线程：我是线程5,剩余：65	msg线程：我是线程5,剩余：61	msg线程：我是线程5,剩余：55	msg线程：我是线程5,剩余：50	msg线程：我是线程5,剩余：46	msg线程：我是线程5,剩余：40	msg线程：我是线程5,剩余：35	msg线程：我是线程5,剩余：31	msg线程：我是线程5,剩余：27	msg线程：我是线程5,剩余：20	msg线程：我是线程5,剩余：15	msg线程：我是线程5,剩余：10	msg线程：我是线程5,剩余：7	msg线程：我是线程5,剩余：2
