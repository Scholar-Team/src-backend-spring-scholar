package com.scholar.mapper;

import org.springframework.stereotype.Component;

import com.scholar.dto.GroupDTO;
import com.scholar.model.Group;
import com.scholar.request.GroupRequest;

@Component
public class GroupMapper extends BaseMapper<Group, GroupDTO, GroupRequest> {

	public GroupMapper() {
		super(Group.class, GroupDTO.class, GroupRequest.class);
	}
}
