/*package com.staycloud.citiccloud.api.action;

import com.staycloud.citiccloud.api.ActionExecuteException;
import com.staycloud.citiccloud.api.domain.ResourceCreateRequest;
import com.staycloud.citiccloud.api.service.ResourceCreateService;
import com.staycloud.citiccloud.api.support.SimpleAction;
import com.staycloud.citiccloud.api.support.SimpleActionResponse;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

*//**
 * @author lulin
 * @date 2016/6/12.
 *//*
@Service
public class ResourceCreateAction extends SimpleAction<ResourceCreateRequest, SimpleActionResponse> {
    @Inject
    private ResourceCreateService resourceCreateService;

    @Override
    public SimpleActionResponse execute(ResourceCreateRequest request) throws ActionExecuteException {
        return resourceCreateService.createInstance(request);
    }
}
*/