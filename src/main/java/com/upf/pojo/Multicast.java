package com.upf.pojo;

import com.upf.dto.MulticastDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Multicast {

    private Long id;

    private Long groupId;

    private List<String> ipFrom;

    private List<String> ipTo;

    private Date gmt_created;

    private Date gmt_modified;

}
