package com.wnc.news.api.common;

import java.util.List;

import voa.VoaNewsInfo;

public interface VoaApi
{
    public List<VoaNewsInfo> getAllNewsWithContent();

    public void setMaxPages(int max);
}
