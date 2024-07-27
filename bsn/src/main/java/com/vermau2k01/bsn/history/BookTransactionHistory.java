package com.vermau2k01.bsn.history;

import com.vermau2k01.bsn.common.BaseEntity;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class BookTransactionHistory extends BaseEntity {

    private boolean returned;
    private boolean returnApproved;

}
