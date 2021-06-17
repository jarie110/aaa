package org.jeecg.pojo;


import org.jeecg.modules.rebate.entity.InsuranceRebateRatio;

import java.util.Date;

public class RebatePo extends InsuranceRebateRatio {
    private Date createTimeBegin;
    private Date createTimeEnd;

    public RebatePo(Date createTimeBegin, Date createTimeEnd) {
        super();
        this.createTimeBegin = createTimeBegin;
        this.createTimeEnd = createTimeEnd;
    }

    public Date getCreateTimeBegin() {
        return createTimeBegin;
    }

    public void setCreateTimeBegin(Date createTimeBegin) {
        this.createTimeBegin = createTimeBegin;
    }

    public Date getCreateTimeEnd() {
        return createTimeEnd;
    }

    public void setCreateTimeEnd(Date createTimeEnd) {
        this.createTimeEnd = createTimeEnd;
    }
}
