package com.busy.engine.service;

import com.busy.engine.entity.LocalizedString;
import com.busy.engine.vo.Result;
import java.util.List;

public interface LocalizedStringService
{

    public Result<LocalizedString> find(String userName, Integer id);
    public Result<List<LocalizedString>> findAll(String userName);
    public Result<LocalizedString> store(String userName, Integer localizedStringId, String locale, String stringValue, Integer textStringId);
    public Result<LocalizedString> remove(String userName, Integer id);
}
